package com.carlosdiestro.friend.domain

import kotlinx.coroutines.flow.Flow

interface FriendRequestRepository {
    suspend fun create(request: FriendRequest)
    suspend fun delete(request: FriendRequest)
    fun getAllSent(): Flow<List<FriendRequest>>
    fun getAllSentIds(): Flow<List<String>>
    fun getAllReceived(receiverId: String): Flow<List<FriendRequest>>
}
