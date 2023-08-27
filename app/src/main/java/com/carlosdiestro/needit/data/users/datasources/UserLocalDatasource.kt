package com.carlosdiestro.needit.data.users.datasources

import com.carlosdiestro.needit.preferences.UserPrefs
import kotlinx.coroutines.flow.Flow

interface UserLocalDatasource {

    val isUserGuest: Flow<Boolean>
    suspend fun updateIsUserGuest()

    val userInfo: Flow<UserPrefs>
    suspend fun updateUserInfo(userPrefs: UserPrefs)
}