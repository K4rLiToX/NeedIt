package com.carlosdiestro.needit.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.carlosdiestro.needit.database.entities.WishEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WishDao {
    @Query("SELECT * FROM wish_table")
    fun getAll(): Flow<List<WishEntity>>

    @Query("SELECT * FROM wish_table WHERE id = :id")
    suspend fun getWish(id: Long): WishEntity

    @Upsert
    suspend fun upsert(entity: WishEntity)

    @Query("DELETE FROM wish_table WHERE id = :id")
    suspend fun remove(id: Long)

    @Query("UPDATE wish_table SET is_shared = 1 WHERE id = :id")
    suspend fun share(id: Long)

    @Query("UPDATE wish_table SET is_shared = 0 WHERE id = :id")
    suspend fun lock(id: Long)
}