package com.carlosdiestro.friend.usecases

import com.carlosdiestro.friend.domain.Friend
import com.carlosdiestro.friend.domain.FriendRepository
import com.carlosdiestro.friend.domain.FriendRequest
import com.carlosdiestro.friend.domain.FriendRequestRepository
import javax.inject.Inject

class SendFriendRequestUseCase @Inject constructor(
    private val friendRequestRepository: FriendRequestRepository,
    private val friendRepository: FriendRepository
) {

    suspend operator fun invoke(
        friend: Friend,
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
                receiverId = friend.id
            )
        )
        friendRepository.upsert(friend)
    }
}