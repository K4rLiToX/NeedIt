package com.carlosdiestro.needit.di

import com.carlosdiestro.wish.data.repository.ImageRepositoryImpl
import com.carlosdiestro.wish.data.repository.WishRepositoryImpl
import com.carlosdiestro.wish.domain.repository.ImageRepository
import com.carlosdiestro.wish.domain.repository.WishRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface WishModule {

    @Singleton
    @Binds
    fun bindWishRepository(impl: WishRepositoryImpl): WishRepository

    @Singleton
    @Binds
    fun bindImageRepository(impl: ImageRepositoryImpl): ImageRepository
}