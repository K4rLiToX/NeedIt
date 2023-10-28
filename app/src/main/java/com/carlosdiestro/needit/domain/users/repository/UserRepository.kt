package com.carlosdiestro.needit.domain.users.repository

import com.carlosdiestro.needit.domain.users.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val user: Flow<User>
    suspend fun upsertUser(user: User)
    suspend fun updateUser(user: User)
}