package com.carlosdiestro.localdatabase.wishes

import kotlinx.coroutines.flow.Flow

interface WishLocalDatasource {
    val wishes: Flow<List<WishEntity>>

    val sharedWishes: Flow<List<WishEntity>>
    fun getWish(id: String): Flow<WishEntity>
    suspend fun create(wish: WishEntity)
    suspend fun update(wish: WishEntity)
    suspend fun delete(wish: WishEntity)
}