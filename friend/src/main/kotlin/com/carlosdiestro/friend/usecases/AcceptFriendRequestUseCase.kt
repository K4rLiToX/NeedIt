package com.carlosdiestro.friend.usecases

import com.carlosdiestro.friend.domain.Friend
import com.carlosdiestro.friend.domain.FriendRepository
import com.carlosdiestro.friend.domain.FriendRequest
import com.carlosdiestro.friend.domain.FriendRequestRepository
import com.carlosdiestro.friend.domain.Friendship
import com.carlosdiestro.friend.domain.FriendshipRepository
import javax.inject.Inject

class AcceptFriendRequestUseCase @Inject constructor(
    private val friendRequestRepository: FriendRequestRepository,
    private val friendRepository: FriendRepository,
    private val friendshipRepository: FriendshipRepository
) {

    suspend operator fun invoke(
        request: FriendRequest,
        username: String,
        email: String,
        profilePictureUrl: String
    ) {
        friendRequestRepository.delete(request)
        friendRepository.upsert(
            Friend(
                id = request.senderId,
                username = request.senderUsername,
                email = request.senderEmail,
                profilePictureUrl = request.senderProfilePictureUrl
            )
        )
        friendshipRepository.create(
            Friendship(
                user1Id = request.receiverId,
                user1Username = username,
                user1Email = email,
                user1ProfilePictureUrl = profilePictureUrl,
                user2Id = request.senderId,
                user2Username = request.senderUsername,
                user2Email = request.senderEmail,
                user2ProfilePictureUrl = request.senderProfilePictureUrl
            )
        )
    }
}