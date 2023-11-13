package com.carlosdiestro.needit.data.users

import com.carlosdiestro.needit.datastore.models.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserLocalDatasource {
    val user: Flow<UserPreferences>
    suspend fun upsert(user: UserPreferences)
    suspend fun clear()
}