package com.carlosdiestro.friend.data

import com.carlosdiestro.friend.domain.Friend
import com.carlosdiestro.friend.domain.FriendRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FriendRepositoryImpl @Inject constructor(
    private val localDataSource: FriendLocalDatasource
) : FriendRepository {

    override suspend fun upsert(friend: Friend) = localDataSource.upsert(friend)

    override suspend fun delete(friend: Friend) = localDataSource.delete(friend)

    override fun getAll(): Flow<List<Friend>> = localDataSource.getAll()

    override fun getAllIds(): Flow<List<String>> = localDataSource.getAllIds()
}