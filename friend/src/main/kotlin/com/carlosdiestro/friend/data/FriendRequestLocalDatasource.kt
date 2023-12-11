package com.carlosdiestro.friend.data

import com.carlosdiestro.friend.domain.FriendRequest
import kotlinx.coroutines.flow.Flow

interface FriendRequestLocalDatasource {
    suspend fun create(request: FriendRequest)
    suspend fun delete(request: FriendRequest)
    fun getAll(): Flow<List<FriendRequest>>
}