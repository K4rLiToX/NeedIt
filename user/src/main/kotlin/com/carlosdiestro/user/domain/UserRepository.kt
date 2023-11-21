package com.carlosdiestro.user.domain

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val user: Flow<User>
    suspend fun upsert(user: User)
    suspend fun clear()
}