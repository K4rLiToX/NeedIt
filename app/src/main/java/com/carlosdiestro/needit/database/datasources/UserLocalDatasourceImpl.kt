package com.carlosdiestro.needit.database.datasources

import com.carlosdiestro.needit.data.preferences.NeedItPreferences
import com.carlosdiestro.needit.data.users.datasources.UserLocalDatasource
import com.carlosdiestro.needit.preferences.UserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalDatasourceImpl @Inject constructor(
    private val preferences: NeedItPreferences
) : UserLocalDatasource {

    override val userInfo: Flow<UserPreferences> = preferences.user

    override suspend fun updateUserInfo(userPreferences: UserPreferences) =
        preferences.updateUserInfo(userPreferences)

    override suspend fun cleanSignedInUser() =
        preferences.cleanPreferences()
}