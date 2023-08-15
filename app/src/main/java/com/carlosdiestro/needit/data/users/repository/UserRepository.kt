package com.carlosdiestro.needit.data.users.repository

import com.carlosdiestro.needit.domain.users.User

interface UserRepository {

    suspend fun createUser(user: User)
}