package com.carlosdiestro.needit.files

import android.content.ContentResolver
import android.content.Context
import com.carlosdiestro.needit.core.di.IoDispatcher
import com.carlosdiestro.needit.data.wishes.datasources.FileManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FileModule {
    @Provides
    @Singleton
    fun provideContentResolver(@ApplicationContext context: Context): ContentResolver =
        context.contentResolver

    @Provides
    @Singleton
    fun provideFileManager(
        contentResolver: ContentResolver,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): FileManager = FileManagerImpl(contentResolver, dispatcher)
}