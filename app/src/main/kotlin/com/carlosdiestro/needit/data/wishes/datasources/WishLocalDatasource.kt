package com.carlosdiestro.needit.data.wishes.datasources

import com.carlosdiestro.needit.framework.database.wishes.WishEntity
import kotlinx.coroutines.flow.Flow

interface WishLocalDatasource {
    val wishes: Flow<List<WishEntity>>

    val sharedWishes: Flow<List<WishEntity>>
    fun getWish(id: String): Flow<WishEntity>
    suspend fun create(wish: WishEntity)
    suspend fun update(wish: WishEntity)
    suspend fun delete(wish: WishEntity)
}