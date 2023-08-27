package com.carlosdiestro.needit.data.preferences

import com.carlosdiestro.needit.preferences.UserPrefs
import kotlinx.coroutines.flow.Flow

interface NeedItPreferences {

    val isUserGuest: Flow<Boolean>
    suspend fun updateIsUserGuest()

    val userPrefs: Flow<UserPrefs>
    suspend fun updateUserInfo(userPrefs: UserPrefs)
}