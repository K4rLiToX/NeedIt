package com.carlosdiestro.needit.data.users.repository

import com.carlosdiestro.needit.domain.users.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val user: Flow<User>
    val isUserGuest: Flow<Boolean>
    suspend fun createUser(user: User)
}