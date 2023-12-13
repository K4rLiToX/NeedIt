package com.carlosdiestro.user.domain

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val currentUser: Flow<User>
    suspend fun getCurrentUser(): User
    suspend fun getCurrentUserId(): String
    suspend fun upsert(user: User)
    fun getAll(): Flow<List<User>>
    suspend fun clear()
}