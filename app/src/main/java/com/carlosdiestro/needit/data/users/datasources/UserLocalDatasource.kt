package com.carlosdiestro.needit.data.users.datasources

import com.carlosdiestro.needit.preferences.UserInfo
import kotlinx.coroutines.flow.Flow

interface UserLocalDatasource {

    val isUserGuest: Flow<Boolean>
    suspend fun updateIsUserGuest()

    val userInfo: Flow<UserInfo>
    suspend fun updateUserInfo(userInfo: UserInfo)
}