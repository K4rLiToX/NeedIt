package com.carlosdiestro.friend.domain

import kotlinx.coroutines.flow.Flow

interface FriendRequestRepository {
    fun create(request: FriendRequest)
    fun delete(request: FriendRequest)
    fun getAll(receiverId: String): Flow<List<FriendRequest>>
}
