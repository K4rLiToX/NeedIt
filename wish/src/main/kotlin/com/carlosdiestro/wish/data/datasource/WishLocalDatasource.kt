package com.carlosdiestro.wish.data.datasource

import com.carlosdiestro.wish.domain.model.Wish
import kotlinx.coroutines.flow.Flow

interface WishLocalDatasource {
    val wishes: Flow<List<Wish>>
    fun getWish(id: String): Flow<Wish>
    suspend fun create(wish: Wish)
    suspend fun update(wish: Wish)
    suspend fun delete(wish: Wish)
}