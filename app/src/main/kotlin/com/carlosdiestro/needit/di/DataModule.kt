package com.carlosdiestro.needit.di

import com.carlosdiestro.needit.data.theme_config.ThemeConfigRepositoryImpl
import com.carlosdiestro.needit.data.users.UserRepositoryImpl
import com.carlosdiestro.wish.data.repository.ImageRepositoryImpl
import com.carlosdiestro.wish.data.repository.WishRepositoryImpl
import com.carlosdiestro.needit.domain.theme_config.repository.ThemeConfigRepository
import com.carlosdiestro.needit.domain.users.repository.UserRepository
import com.carlosdiestro.wish.domain.repository.ImageRepository
import com.carlosdiestro.wish.domain.repository.WishRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Singleton
    @Binds
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    fun bindWishRepository(impl: WishRepositoryImpl): WishRepository

    @Singleton
    @Binds
    fun bindImageRepository(impl: ImageRepositoryImpl): ImageRepository

    @Singleton
    @Binds
    fun bindThemeConfigRepository(impl: ThemeConfigRepositoryImpl): ThemeConfigRepository
}