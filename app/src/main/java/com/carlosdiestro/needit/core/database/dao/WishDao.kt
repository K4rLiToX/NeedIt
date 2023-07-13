package com.carlosdiestro.needit.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.carlosdiestro.needit.core.database.entities.WishEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WishDao {
    @Query("SELECT * FROM wish_table")
    fun getAll(): Flow<List<WishEntity>>

    @Query("SELECT * FROM wish_table WHERE id = :id")
    suspend fun getWish(id: Long): WishEntity

    @Insert
    suspend fun insert(entity: WishEntity)

    @Query("DELETE FROM wish_table WHERE id = :id")
    suspend fun remove(id: Long)

    @Query("UPDATE wish_table SET is_shared = 1 WHERE id = :id")
    suspend fun share(id: Long)

    @Query("UPDATE wish_table SET is_shared = 0 WHERE id = :id")
    suspend fun lock(id: Long)
}