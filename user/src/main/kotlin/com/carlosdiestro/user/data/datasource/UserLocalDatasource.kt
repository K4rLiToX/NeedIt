package com.carlosdiestro.user.data.datasource

import com.carlosdiestro.user.domain.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDatasource {
    val user: Flow<User>
    suspend fun upsert(user: User)
    suspend fun clear()
}