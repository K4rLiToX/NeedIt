package com.carlosdiestro.needit.di

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.carlosdiestro.device_storage.ImageLocalDatasourceImpl
import com.carlosdiestro.device_storage.ImageStorageDao
import com.carlosdiestro.wish.data.datasource.ImageLocalDatasource
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
    fun provideImageStorageDao(
        imageStorage: Uri,
        contentResolver: ContentResolver,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): ImageStorageDao =
        ImageStorageDao(
            imageStorage,
            contentResolver,
            ioDispatcher
        )

    @Provides
    @Singleton
    fun provideImageLocalDatasource(dao: ImageStorageDao): ImageLocalDatasource =
        ImageLocalDatasourceImpl(dao)
}