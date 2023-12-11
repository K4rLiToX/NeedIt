package com.carlosdiestro.local_database.friend_requests

import com.carlosdiestro.friend.data.FriendRequestLocalDatasource
import com.carlosdiestro.friend.domain.FriendRequest
import com.carlosdiestro.friend.domain.FriendRequestStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FriendRequestLocalDatasourceImpl @Inject constructor(
    private val dao: FriendRequestDao
) : FriendRequestLocalDatasource {

    override suspend fun create(request: FriendRequest) = dao.create(request.asEntity())

    override suspend fun delete(request: FriendRequest) = dao.delete(request.asEntity())

    override fun getAll(): Flow<List<FriendRequest>> = dao.getAll().asDomain()
}

internal fun FriendRequest.asEntity(): FriendRequestEntity = FriendRequestEntity(
    senderId = senderId,
    senderUsername = senderUsername,
    senderEmail = senderEmail,
    senderProfilePictureUrl = senderProfilePictureUrl,
    receiverId = receiverId,
    receiverUsername = receiverUsername,
    receiverEmail = receiverEmail,
    receiverProfilePictureUrl = receiverProfilePictureUrl
)

internal fun FriendRequestEntity.asDomain(): FriendRequest = FriendRequest(
    senderId = senderId,
    senderUsername = senderUsername,
    senderEmail = senderEmail,
    senderProfilePictureUrl = senderProfilePictureUrl,
    receiverId = receiverId,
    receiverUsername = receiverUsername,
    receiverEmail = receiverEmail,
    receiverProfilePictureUrl = receiverProfilePictureUrl,
    status = FriendRequestStatus.Pending
)

internal fun List<FriendRequestEntity>.asDomain(): List<FriendRequest> = this.map { it.asDomain() }

internal fun Flow<List<FriendRequestEntity>>.asDomain(): Flow<List<FriendRequest>> =
    this.map { it.asDomain() }