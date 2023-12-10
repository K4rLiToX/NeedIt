package com.carlosdiestro.friend.usecases

import com.carlosdiestro.friend.domain.Friend
import com.carlosdiestro.friend.domain.FriendRepository
import com.carlosdiestro.friend.domain.FriendRequest
import javax.inject.Inject

class AddFriendUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {

    suspend operator fun invoke(request: FriendRequest) = friendRepository.upsert(
        userId = request.senderId,
        friend = Friend(
            id = request.receiverId,
            username = request.receiverUsername,
            email = request.receiverEmail,
            profilePictureUrl = request.receiverProfilePictureUrl
        )
    )
}