package com.carlosdiestro.needit.data.users

import com.carlosdiestro.needit.domain.users.User

interface UserRepository {

    suspend fun createUser(user: User)
}