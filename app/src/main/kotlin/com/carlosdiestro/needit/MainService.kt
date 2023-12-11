package com.carlosdiestro.needit

import com.carlosdiestro.app_settings.domain.repository.ThemeConfigRepository
import com.carlosdiestro.auth.AuthClient
import com.carlosdiestro.friend.domain.Friend
import com.carlosdiestro.friend.domain.FriendRepository
import com.carlosdiestro.friend.domain.FriendRequest
import com.carlosdiestro.friend.domain.FriendRequestRepository
import com.carlosdiestro.friend.domain.FriendRequestStatus
import com.carlosdiestro.user.domain.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainService @Inject constructor(
    private val userRepository: UserRepository,
    private val friendRequestRepository: FriendRequestRepository,
    private val friendRepository: FriendRepository,
    themeConfigRepository: ThemeConfigRepository,
    authClient: AuthClient
) {

    val isSignedIn = authClient.signedInUser != null

    val profilePictureUrl = userRepository.currentUser.map { it.profilePictureUrl }

    val themeConfig = themeConfigRepository.themeConfig

    suspend fun syncFriends() {
        val userId = userRepository.getCurrentUserId()
        friendRequestRepository.getAllSent(
            userId = userId,
            remote = true
        )
            .first()
            .forEach { request ->
                if (request.status == FriendRequestStatus.Accepted) {
                    addFriend(request)
                }
                deleteFriendRequest(request)
            }
    }

    private suspend fun addFriend(request: FriendRequest) {
        friendRepository.upsert(
            userId = request.senderId,
            friend = Friend(
                id = request.receiverId,
                username = request.receiverUsername,
                email = request.receiverEmail,
                profilePictureUrl = request.receiverProfilePictureUrl
            )
        )
    }

    private suspend fun deleteFriendRequest(request: FriendRequest) {
        friendRequestRepository.delete(request)
    }
}