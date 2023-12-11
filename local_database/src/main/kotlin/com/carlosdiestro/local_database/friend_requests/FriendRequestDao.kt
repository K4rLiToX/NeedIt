package com.carlosdiestro.local_database.friend_requests

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FriendRequestDao {

    @Insert
    suspend fun create(request: FriendRequestEntity)

    @Delete
    suspend fun delete(request: FriendRequestEntity)

    @Query(
        """
            SELECT *
            FROM friend_request_table
        """
    )
    fun getAll(): Flow<List<FriendRequestEntity>>
}