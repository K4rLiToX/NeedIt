package com.carlosdiestro.needit.di

import com.carlosdiestro.app_settings.data.ThemeConfigRepositoryImpl
import com.carlosdiestro.app_settings.domain.repository.ThemeConfigRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface AppSettingsModule {

    @Singleton
    @Binds
    fun bindThemeConfigRepository(impl: ThemeConfigRepositoryImpl): ThemeConfigRepository
}