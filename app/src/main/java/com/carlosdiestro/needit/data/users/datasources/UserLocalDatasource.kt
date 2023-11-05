package com.carlosdiestro.needit.data.users.datasources

import com.carlosdiestro.needit.datastore.models.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserLocalDatasource {

    val userInfo: Flow<UserPreferences>
    suspend fun updateUserInfo(userPreferences: UserPreferences)
    suspend fun cleanSignedInUser()
}