package com.carlosdiestro.needit.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.carlosdiestro.needit.datastore.models.SettingsPreferences
import com.carlosdiestro.needit.datastore.models.UserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class NeedItPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    val user: Flow<UserPreferences> = dataStore.user

    val settings: Flow<SettingsPreferences> = dataStore.settings

    suspend fun updateUser(user: UserPreferences) = dataStore.updateUser(user)

    suspend fun updateUseSystemScheme() = dataStore.updateUseSystemScheme()

    suspend fun updateIsNightMode() = dataStore.updateIsNightMode()

    suspend fun clear() = dataStore.clear()
}