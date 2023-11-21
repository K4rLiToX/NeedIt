package com.carlosdiestro.datastore.user

import kotlinx.coroutines.flow.Flow

interface UserLocalDatasource {
    val user: Flow<UserPreferences>
    suspend fun upsert(user: UserPreferences)
    suspend fun clear()
}