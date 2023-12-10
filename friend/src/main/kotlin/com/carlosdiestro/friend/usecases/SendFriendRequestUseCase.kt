package com.carlosdiestro.friend.usecases

import com.carlosdiestro.friend.domain.FriendRequest
import com.carlosdiestro.friend.domain.FriendRequestRepository
import com.carlosdiestro.friend.domain.FriendRequestStatus
import javax.inject.Inject

class SendFriendRequestUseCase @Inject constructor(
    private val friendRequestRepository: FriendRequestRepository
) {

    suspend operator fun invoke(
        receiverId: String,
        receiverUsername: String,
        receiverEmail: String,
        receiverProfilePictureUrl: String,
        userId: String,
        username: String,
        email: String,
        profilePictureUrl: String
    ) {
        friendRequestRepository.create(
            FriendRequest(
                senderId = userId,
                senderUsername = username,
                senderEmail = email,
                senderProfilePictureUrl = profilePictureUrl,
                receiverId = receiverId,
                receiverUsername = receiverUsername,
                receiverEmail = receiverEmail,
                receiverProfilePictureUrl = receiverProfilePictureUrl,
                status = FriendRequestStatus.Pending
            )
        )
    }
}