package com.carlosdiestro.needit.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.carlosdiestro.needit.core.database.entities.WishEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WishDao {
    @Query("SELECT * FROM wish_table")
    fun getAll(): Flow<List<WishEntity>>
}