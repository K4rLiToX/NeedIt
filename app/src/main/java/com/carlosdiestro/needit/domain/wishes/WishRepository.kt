package com.carlosdiestro.needit.domain.wishes

import kotlinx.coroutines.flow.Flow

interface WishRepository {
    val wishes: Flow<List<Wish>>
    suspend fun getWish(id: Long): Wish
    suspend fun insertWish(wish: Wish)
    suspend fun removeWish(id: Long)
    suspend fun shareWish(id: Long)
    suspend fun lockWish(id: Long)
}