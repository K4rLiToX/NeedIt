package com.carlosdiestro.user.data.datasource

import com.carlosdiestro.user.domain.User
import kotlinx.coroutines.flow.Flow

interface UserRemoteDatasource {
    fun upsert(user: User)
    fun getAll(): Flow<List<User>>
}