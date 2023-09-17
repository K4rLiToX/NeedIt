package com.carlosdiestro.needit.data.wishes.datasources

import com.carlosdiestro.needit.domain.wishes.Wish
import kotlinx.coroutines.flow.Flow

interface WishLocalDatasource {
    val wishes: Flow<List<Wish>>
    val sharedWishes: Flow<List<Wish>>
    suspend fun getWish(id: Long): Wish
    suspend fun upsertWish(wish: Wish)
    suspend fun removeWish(id: Long)
    suspend fun shareWish(id: Long, cloudId: String)
    suspend fun lockWish(id: Long)
}