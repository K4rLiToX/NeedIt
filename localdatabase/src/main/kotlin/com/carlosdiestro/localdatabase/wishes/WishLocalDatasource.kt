package com.carlosdiestro.localdatabase.wishes

import com.carlosdiestro.localdatabase.wishes.WishEntity
import kotlinx.coroutines.flow.Flow

interface WishLocalDatasource {
    val wishes: Flow<List<com.carlosdiestro.localdatabase.wishes.WishEntity>>

    val sharedWishes: Flow<List<com.carlosdiestro.localdatabase.wishes.WishEntity>>
    fun getWish(id: String): Flow<com.carlosdiestro.localdatabase.wishes.WishEntity>
    suspend fun create(wish: com.carlosdiestro.localdatabase.wishes.WishEntity)
    suspend fun update(wish: com.carlosdiestro.localdatabase.wishes.WishEntity)
    suspend fun delete(wish: com.carlosdiestro.localdatabase.wishes.WishEntity)
}