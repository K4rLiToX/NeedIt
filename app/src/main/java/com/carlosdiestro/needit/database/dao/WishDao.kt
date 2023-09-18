package com.carlosdiestro.needit.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.carlosdiestro.needit.database.entities.WishEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WishDao {
    @Query("SELECT * FROM wish_table")
    fun getAll(): Flow<List<WishEntity>>

    @Query("SELECT * FROM wish_table WHERE is_shared = 1")
    fun getShared(): Flow<List<WishEntity>>

    @Query("SELECT * FROM wish_table WHERE id = :id")
    fun getWish(id: Long): Flow<WishEntity>

    @Insert
    suspend fun insert(wish: WishEntity): Long

    @Update
    suspend fun update(wish: WishEntity)

    @Query("DELETE FROM wish_table WHERE id = :id")
    suspend fun remove(id: Long)

    @Query("UPDATE wish_table SET is_shared = 1, cloud_id = :cloudId WHERE id = :id")
    suspend fun share(id: Long, cloudId: String)

    @Query("UPDATE wish_table SET is_shared = 0, cloud_id = '' WHERE id = :id")
    suspend fun lock(id: Long)
}