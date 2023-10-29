package com.carlosdiestro.needit.data.preferences

import com.carlosdiestro.needit.preferences.UserPrefs
import kotlinx.coroutines.flow.Flow

interface NeedItPreferences {

    val userInfo: Flow<UserPrefs>
    suspend fun updateUserInfo(userPrefs: UserPrefs)
    suspend fun cleanPreferences()
}