package com.carlosdiestro.local_database.friends

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FriendDao {

    @Upsert
    suspend fun upsert(friend: FriendEntity)

    @Delete
    suspend fun delete(friend: FriendEntity)

    @Query(
        """
            SELECT *
            FROM friends_table
        """
    )
    fun getAll(): Flow<List<FriendEntity>>
}