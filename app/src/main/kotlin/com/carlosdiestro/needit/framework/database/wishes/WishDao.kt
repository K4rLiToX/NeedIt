package com.carlosdiestro.needit.framework.database.wishes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
internal interface WishDao {
    @Insert
    suspend fun create(wish: WishEntity)

    @Query(
        """
            SELECT *
            FROM WISH_TABLE
        """
    )
    fun getAll(): Flow<List<WishEntity>>

    @Query(
        """
            SELECT * 
            FROM wish_table 
            WHERE is_shared = 1
        """
    )
    fun getShared(): Flow<List<WishEntity>>

    @Query(
        """
            SELECT * 
            FROM wish_table 
            WHERE id = :id
        """
    )
    fun getWish(id: String): Flow<WishEntity>

    @Update
    suspend fun update(wish: WishEntity)


    @Delete
    suspend fun delete(wish: WishEntity)
}