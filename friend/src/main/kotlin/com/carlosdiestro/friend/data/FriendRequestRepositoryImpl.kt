package com.carlosdiestro.friend.data

import com.carlosdiestro.friend.domain.FriendRequest
import com.carlosdiestro.friend.domain.FriendRequestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FriendRequestRepositoryImpl @Inject constructor(
    private val remoteDatasource: FriendRequestRemoteDatasource
) : FriendRequestRepository {
    override fun create(request: FriendRequest) = remoteDatasource.create(request)

    override fun delete(request: FriendRequest) = remoteDatasource.delete(request)

    override fun getAll(receiverId: String): Flow<List<FriendRequest>> =
        remoteDatasource.getAll(receiverId)
}