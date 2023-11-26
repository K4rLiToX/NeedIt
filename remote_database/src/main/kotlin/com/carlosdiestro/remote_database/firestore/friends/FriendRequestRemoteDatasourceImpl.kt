package com.carlosdiestro.remote_database.firestore.friends

import com.carlosdiestro.friend.data.FriendRequestRemoteDatasource
import com.carlosdiestro.friend.domain.FriendRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class FriendRequestRemoteDatasourceImpl @Inject constructor(
    private val friendRequestsCollection: FriendRequestsCollection
) : FriendRequestRemoteDatasource {

    override fun create(request: FriendRequest) = friendRequestsCollection.create(request.asDto())

    override fun delete(request: FriendRequest) = friendRequestsCollection.delete(request.asDto())

    override fun getAll(receiverId: String): Flow<List<FriendRequest>> =
        friendRequestsCollection.getAll(receiverId).asDomain()
}

internal fun FriendRequest.asDto(): FriendRequestDto = FriendRequestDto(
    senderId = senderId,
    senderUsername = senderUsername,
    senderEmail = senderEmail,
    senderProfilePictureUrl = senderProfilePictureUrl,
    receiverId = receiverId
)

internal fun FriendRequestDto.asDomain(): FriendRequest = FriendRequest(
    senderId = senderId,
    senderUsername = senderUsername,
    senderEmail = senderEmail,
    senderProfilePictureUrl = senderProfilePictureUrl,
    receiverId = receiverId
)

internal fun List<FriendRequestDto>.asDomain(): List<FriendRequest> = this.map { it.asDomain() }

internal fun Flow<List<FriendRequestDto>>.asDomain(): Flow<List<FriendRequest>> =
    this.map { it.asDomain() }