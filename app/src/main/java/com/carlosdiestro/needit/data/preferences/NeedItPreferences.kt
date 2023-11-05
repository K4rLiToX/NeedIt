package com.carlosdiestro.needit.data.preferences

import com.carlosdiestro.needit.datastore.SettingsPreferences
import com.carlosdiestro.needit.datastore.UserPreferences
import kotlinx.coroutines.flow.Flow

interface NeedItPreferences {

    val user: Flow<UserPreferences>
    val settings: Flow<SettingsPreferences>
    suspend fun updateUserInfo(userPreferences: UserPreferences)
    suspend fun updateUseSystemScheme()
    suspend fun updateIsNightMode()
    suspend fun cleanPreferences()
}