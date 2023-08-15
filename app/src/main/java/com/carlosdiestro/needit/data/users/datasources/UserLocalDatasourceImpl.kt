package com.carlosdiestro.needit.data.users.datasources

import com.carlosdiestro.needit.preferences.NeedItPreferences
import com.carlosdiestro.needit.preferences.UserInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalDatasourceImpl @Inject constructor(
    private val preferences: NeedItPreferences
) : UserLocalDatasource {

    override val isUserGuest: Flow<Boolean> = preferences.isUserGuest

    override suspend fun updateIsUserGuest() = preferences.updateIsUserGuest()

    override val userInfo: Flow<UserInfo> = preferences.userInfo

    override suspend fun updateUserInfo(userInfo: UserInfo) = preferences.updateUserInfo(userInfo)
}