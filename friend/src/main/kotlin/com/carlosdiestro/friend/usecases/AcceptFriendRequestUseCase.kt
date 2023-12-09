package com.carlosdiestro.friend.usecases

import com.carlosdiestro.friend.domain.Friend
import com.carlosdiestro.friend.domain.FriendRepository
import com.carlosdiestro.friend.domain.FriendRequest
import com.carlosdiestro.friend.domain.FriendRequestRepository
import com.carlosdiestro.friend.domain.FriendRequestStatus
import javax.inject.Inject

class AcceptFriendRequestUseCase @Inject constructor(
    private val friendRequestRepository: FriendRequestRepository,
    private val friendRepository: FriendRepository
) {

    suspend operator fun invoke(request: FriendRequest) {
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
}