package com.carlosdiestro.needit.domain.wishes.repository

import com.carlosdiestro.needit.domain.wishes.Wish
import kotlinx.coroutines.flow.Flow

interface WishRepository {
    val wishes: Flow<List<Wish>>
    val sharedWishes: Flow<List<Wish>>
    suspend fun getWish(id: Long): Wish
    suspend fun insertWish(wish: Wish): Long
    suspend fun updateWish(wish: Wish)
    suspend fun removeWish(id: Long, cloudId: String)
    suspend fun shareWish(id: Long)
    suspend fun lockWish(id: Long)
}