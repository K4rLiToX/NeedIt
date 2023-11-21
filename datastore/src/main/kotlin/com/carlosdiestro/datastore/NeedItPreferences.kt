package com.carlosdiestro.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.carlosdiestro.datastore.theme_config.ThemeConfigPreferences
import com.carlosdiestro.datastore.user.UserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class NeedItPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    val user: Flow<UserPreferences> = dataStore.user

    val themeConfig: Flow<ThemeConfigPreferences> = dataStore.themeConfig

    suspend fun upsertUser(user: UserPreferences) = dataStore.upsertUser(user)

    suspend fun updateThemeConfig(themeConfig: ThemeConfigPreferences) =
        dataStore.updateThemeConfig(themeConfig)

    suspend fun clear() = dataStore.clear()
}