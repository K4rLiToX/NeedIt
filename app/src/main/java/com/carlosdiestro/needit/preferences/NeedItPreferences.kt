package com.carlosdiestro.needit.preferences

import kotlinx.coroutines.flow.Flow

interface NeedItPreferences {

    val isUserGuest: Flow<Boolean>
    suspend fun updateIsUserGuest()

    val userInfo: Flow<UserInfo>
    suspend fun updateUserInfo(userInfo: UserInfo)
}