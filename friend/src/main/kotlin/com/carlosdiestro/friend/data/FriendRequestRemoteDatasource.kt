package com.carlosdiestro.friend.data

import com.carlosdiestro.friend.domain.FriendRequest
import kotlinx.coroutines.flow.Flow

interface FriendRequestRemoteDatasource {
    fun create(request: FriendRequest)
    fun delete(request: FriendRequest)
    fun getAll(receiverId: String): Flow<List<FriendRequest>>
}