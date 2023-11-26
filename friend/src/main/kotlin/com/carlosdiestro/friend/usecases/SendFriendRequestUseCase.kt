package com.carlosdiestro.friend.usecases

import com.carlosdiestro.friend.domain.FriendRequest
import com.carlosdiestro.friend.domain.FriendRequestRepository
import javax.inject.Inject

class SendFriendRequestUseCase @Inject constructor(
    private val friendRequestRepository: FriendRequestRepository
) {

    suspend operator fun invoke(
        friendId: String,
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
                receiverId = friendId
            )
        )
    }
}