package com.carlosdiestro.wish.domain.repository

import com.carlosdiestro.wish.domain.model.Wish
import kotlinx.coroutines.flow.Flow

interface WishRepository {
    val wishes: Flow<List<Wish>>
    val sharedWishes: Flow<List<Wish>>
    fun getWish(id: String): Flow<Wish>
    suspend fun create(wish: Wish)
    suspend fun update(wish: Wish)
    suspend fun delete(wish: Wish)
}