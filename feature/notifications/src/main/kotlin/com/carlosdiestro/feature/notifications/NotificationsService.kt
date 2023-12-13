package com.carlosdiestro.feature.notifications

import com.carlosdiestro.friend.domain.Friend
import com.carlosdiestro.friend.domain.FriendRepository
import com.carlosdiestro.friend.domain.FriendRequest
import com.carlosdiestro.friend.domain.FriendRequestRepository
import com.carlosdiestro.friend.domain.FriendRequestStatus
import com.carlosdiestro.user.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NotificationsService @Inject constructor(
    private val userRepository: UserRepository,
    private val friendRequestRepository: FriendRequestRepository,
    private val friendRepository: FriendRepository
) {

    fun getReceivedFriendRequests(): Flow<List<FriendRequest>> = flow {
        val userId = userRepository.getCurrentUserId()
        emitAll(friendRequestRepository.getAllReceived(userId))
    }

    suspend fun accept(request: FriendRequest) = this.run {
        friendRequestRepository.update(
            request.copy(status = FriendRequestStatus.Accepted)
        )
        friendRepository.upsert(
            userId = request.receiverId,
            friend = Friend(
                id = request.senderId,
                username = request.senderUsername,
                email = request.senderEmail,
                profilePictureUrl = request.senderProfilePictureUrl
            )
        )
    }

    suspend fun reject(request: FriendRequest) = friendRequestRepository.update(
        request.copy(status = FriendRequestStatus.Rejected)
    )
}