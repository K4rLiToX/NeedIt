package com.carlosdiestro.friend.data

import com.carlosdiestro.friend.domain.Friend
import com.carlosdiestro.friend.domain.FriendRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FriendRepositoryImpl @Inject constructor(
    private val localDataSource: FriendLocalDatasource,
    private val remoteDatasource: FriendRemoteDatasource
) : FriendRepository {

    override suspend fun upsert(userId: String, friend: Friend) = this.run {
        localDataSource.upsert(friend)
        remoteDatasource.create(userId, friend)
    }

    override suspend fun delete(friend: Friend) = localDataSource.delete(friend)

    override fun getAll(): Flow<List<Friend>> = localDataSource.getAll()
}