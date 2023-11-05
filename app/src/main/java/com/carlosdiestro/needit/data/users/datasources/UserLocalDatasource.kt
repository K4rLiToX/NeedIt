package com.carlosdiestro.needit.data.users.datasources

import com.carlosdiestro.needit.preferences.UserPrefs
import kotlinx.coroutines.flow.Flow

interface UserLocalDatasource {

    val userInfo: Flow<UserPrefs>
    suspend fun updateUserInfo(userPrefs: UserPrefs)
    suspend fun cleanSignedInUser()
}