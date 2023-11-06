package com.carlosdiestro.needit.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.carlosdiestro.needit.core.di.IoDispatcher
import com.carlosdiestro.needit.data.preferences.NeedItPreferencesDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

private const val NEED_IT_PREFERENCES = "need_it_preferences"

@Module
@InstallIn(SingletonComponent::class)
internal object DataStoreModule {

    @Singleton
    @Provides
    fun providesNeedItPreferences(
        @ApplicationContext context: Context,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory
            .create(
                scope = CoroutineScope(SupervisorJob() + ioDispatcher),
                produceFile = {
                    context.preferencesDataStoreFile(NEED_IT_PREFERENCES)
                }
            )
    }

    @Singleton
    @Provides
    fun providesNeedItPreferencesDatasource(
        preferences: NeedItPreferences
    ): NeedItPreferencesDatasource {
        return NeedItPreferencesDatasourceImpl(preferences)
    }
}