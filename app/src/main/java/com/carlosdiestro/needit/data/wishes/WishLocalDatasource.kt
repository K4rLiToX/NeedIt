package com.carlosdiestro.needit.data.wishes

import com.carlosdiestro.needit.domain.wishes.Wish
import kotlinx.coroutines.flow.Flow

interface WishLocalDatasource {
    val wishes: Flow<List<Wish>>
    suspend fun getWish(id: Long): Wish
    suspend fun insertWish(wish: Wish)
    suspend fun removeWish(id: Long)
    suspend fun shareWish(id: Long)
    suspend fun lockWish(id: Long)
}