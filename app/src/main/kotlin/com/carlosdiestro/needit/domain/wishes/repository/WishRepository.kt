package com.carlosdiestro.needit.domain.wishes.repository

import com.carlosdiestro.needit.domain.wishes.Wish
import kotlinx.coroutines.flow.Flow

interface WishRepository {
    val wishes: Flow<List<Wish>>
    val sharedWishes: Flow<List<Wish>>
    fun getWish(id: Long): Flow<Wish>
    suspend fun create(wish: Wish): Long
    suspend fun update(wish: Wish)
    suspend fun delete(id: Long, cloudId: String)
    suspend fun share(id: Long)
    suspend fun lock(id: Long)
}