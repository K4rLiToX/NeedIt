package com.carlosdiestro.friend.domain

import kotlinx.coroutines.flow.Flow

interface FriendRequestRepository {
    suspend fun create(request: FriendRequest)
    suspend fun delete(request: FriendRequest)
    suspend fun update(request: FriendRequest)
    fun getAllSent(userId: String = "", remote: Boolean = false): Flow<List<FriendRequest>>
    fun getAllSentIds(): Flow<List<String>>
    fun getAllReceived(receiverId: String): Flow<List<FriendRequest>>
}
