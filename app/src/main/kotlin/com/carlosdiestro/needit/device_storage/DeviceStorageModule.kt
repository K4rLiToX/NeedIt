package com.carlosdiestro.needit.device_storage

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
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
internal object DeviceStorageModule {
    @Provides
    @Singleton
    fun provideContentResolver(@ApplicationContext context: Context): ContentResolver =
        context.contentResolver

    @Provides
    @Singleton
    fun provideImageStorage(): Uri =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

    @Provides
    @Singleton
    fun provideFileManager(
        imageStorage: Uri,
        contentResolver: ContentResolver,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): FileManager = FileManagerImpl(imageStorage, contentResolver, ioDispatcher)
}