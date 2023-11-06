package com.carlosdiestro.needit.data.preferences

import com.carlosdiestro.needit.datastore.models.SettingsPreferences
import com.carlosdiestro.needit.datastore.models.UserPreferences
import kotlinx.coroutines.flow.Flow

interface NeedItPreferencesDatasource {
    val user: Flow<UserPreferences>
    val settings: Flow<SettingsPreferences>
    suspend fun updateUser(user: UserPreferences)
    suspend fun updateUseSystemScheme()
    suspend fun updateIsNightMode()
    suspend fun clear()
}