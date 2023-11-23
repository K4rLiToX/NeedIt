package com.carlosdiestro.needit.di

import com.carlosdiestro.needit.theme_config.data.ThemeConfigRepositoryImpl
import com.carlosdiestro.needit.theme_config.domain.repository.ThemeConfigRepository
import com.carlosdiestro.user.data.UserRepositoryImpl
import com.carlosdiestro.user.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface UserModule {

    @Singleton
    @Binds
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    fun bindThemeConfigRepository(impl: ThemeConfigRepositoryImpl): ThemeConfigRepository
}