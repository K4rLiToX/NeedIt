package com.carlosdiestro.needit.preferences

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface PreferencesModule {

    @Singleton
    @Binds
    fun bindNeedItPreferences(impl: NeedItPreferencesImpl): NeedItPreferences
}