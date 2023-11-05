package com.carlosdiestro.needit.datastore

import com.carlosdiestro.needit.data.preferences.NeedItPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataStoreModule {

    @Singleton
    @Binds
    fun bindNeedItPreferences(impl: NeedItPreferencesImpl): NeedItPreferences
}