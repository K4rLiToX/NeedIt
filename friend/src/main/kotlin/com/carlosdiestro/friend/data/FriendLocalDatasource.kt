package com.carlosdiestro.friend.data

import com.carlosdiestro.friend.domain.Friend
import kotlinx.coroutines.flow.Flow

interface FriendLocalDatasource {
    suspend fun upsert(friend: Friend)
    suspend fun delete(friend: Friend)
    fun getAll(): Flow<List<Friend>>
}