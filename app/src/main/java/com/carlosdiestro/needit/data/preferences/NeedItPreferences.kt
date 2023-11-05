package com.carlosdiestro.needit.data.preferences

import com.carlosdiestro.needit.preferences.SettingsPrefs
import com.carlosdiestro.needit.preferences.UserPrefs
import kotlinx.coroutines.flow.Flow

interface NeedItPreferences {

    val userInfo: Flow<UserPrefs>
    val settings: Flow<SettingsPrefs>
    suspend fun updateUserInfo(userPrefs: UserPrefs)
    suspend fun cleanPreferences()
}