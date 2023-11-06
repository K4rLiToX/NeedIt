package com.carlosdiestro.needit.domain.users.repository

import com.carlosdiestro.needit.domain.users.User

interface UserRepository {
    suspend fun upsertUser(user: User)
}