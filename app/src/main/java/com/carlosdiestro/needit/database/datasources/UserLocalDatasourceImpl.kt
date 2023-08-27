package com.carlosdiestro.needit.database.datasources

import com.carlosdiestro.needit.data.users.datasources.UserLocalDatasource
import com.carlosdiestro.needit.data.preferences.NeedItPreferences
import com.carlosdiestro.needit.preferences.UserPrefs
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalDatasourceImpl @Inject constructor(
    private val preferences: NeedItPreferences
) : UserLocalDatasource {

    override val isUserGuest: Flow<Boolean> = preferences.isUserGuest

    override suspend fun updateIsUserGuest() = preferences.updateIsUserGuest()

    override val userInfo: Flow<UserPrefs> = preferences.userInfo

    override suspend fun updateUserInfo(userPrefs: UserPrefs) = preferences.updateUserInfo(userPrefs)
}