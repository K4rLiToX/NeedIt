package com.carlosdiestro.wish.domain.repository

import com.carlosdiestro.wish.domain.model.Wish
import kotlinx.coroutines.flow.Flow

interface WishRepository {
    suspend fun create(wish: Wish)
    suspend fun update(wish: Wish)
    suspend fun delete(wish: Wish)
    fun getCurrentUserWishes(): Flow<List<Wish>>
    fun getCurrentUserWish(id: String): Flow<Wish>
    fun getFriendWishes(userId: String): Flow<List<Wish>>
    fun getFriendWish(id: String): Flow<Wish?>
}