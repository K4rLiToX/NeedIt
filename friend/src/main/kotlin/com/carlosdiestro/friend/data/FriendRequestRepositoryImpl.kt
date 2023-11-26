package com.carlosdiestro.friend.data

import com.carlosdiestro.friend.domain.FriendRequest
import com.carlosdiestro.friend.domain.FriendRequestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FriendRequestRepositoryImpl @Inject constructor(
    private val localDatasource: FriendRequestLocalDatasource,
    private val remoteDatasource: FriendRequestRemoteDatasource
) : FriendRequestRepository {
    override suspend fun create(request: FriendRequest) {
        localDatasource.create(request)
        remoteDatasource.create(request)
    }

    override suspend fun delete(request: FriendRequest) {
        localDatasource.delete(request)
        remoteDatasource.delete(request)
    }

    override fun getAllSent(): Flow<List<FriendRequest>> = localDatasource.getAll()

    override fun getAllSentIds(): Flow<List<String>> = localDatasource.getAllIds()

    override fun getAllReceived(receiverId: String): Flow<List<FriendRequest>> =
        remoteDatasource.getAllReceived(receiverId)
}