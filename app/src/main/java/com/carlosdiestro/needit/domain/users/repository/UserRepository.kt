package com.carlosdiestro.needit.domain.users.repository

import com.carlosdiestro.needit.domain.users.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun upsertUser(user: User)
}