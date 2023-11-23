package com.carlosdiestro.needit.di

import com.carlosdiestro.feature.upsert_wish.ImageCompressor
import com.carlosdiestro.needit.core.di.DefaultDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeatureModule {

    @Provides
    @Singleton
    fun provideImageCompressor(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ) : ImageCompressor = ImageCompressor(defaultDispatcher)
}