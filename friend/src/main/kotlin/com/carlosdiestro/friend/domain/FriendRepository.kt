package com.carlosdiestro.friend.domain

import kotlinx.coroutines.flow.Flow

interface FriendRepository {
    suspend fun upsert(userId: String, friend: Friend)
    suspend fun delete(friend: Friend)
    fun getAll(): Flow<List<Friend>>
    fun getAllIds(): Flow<List<String>>
}