package com.carlosdiestro.friend.data

import com.carlosdiestro.friend.domain.FriendRequest
import kotlinx.coroutines.flow.Flow

interface FriendRequestRemoteDatasource {
    fun upsert(request: FriendRequest)
    fun delete(request: FriendRequest)
    fun getAllReceived(receiverId: String): Flow<List<FriendRequest>>
    fun getAllSent(senderId: String): Flow<List<FriendRequest>>
}