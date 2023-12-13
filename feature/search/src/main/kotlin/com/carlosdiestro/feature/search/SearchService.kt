package com.carlosdiestro.feature.search

import com.carlosdiestro.design_system.lists.FollowableUser
import com.carlosdiestro.design_system.lists.FollowableUserStatus
import com.carlosdiestro.friend.domain.FriendRepository
import com.carlosdiestro.friend.domain.FriendRequest
import com.carlosdiestro.friend.domain.FriendRequestRepository
import com.carlosdiestro.friend.domain.FriendRequestStatus
import com.carlosdiestro.user.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchService @Inject constructor(
    private val userRepository: UserRepository,
    private val friendRequestRepository: FriendRequestRepository,
    private val friendRepository: FriendRepository
) {

    val isUserAnonymous = userRepository.currentUser.map { it.isAnonymous }

    fun getFollowableUsers(): Flow<List<FollowableUser>> {
        val usersFlow = userRepository.getAll()
        val friendIdsFlow = friendRepository.getAll().map { it.map { friend -> friend.id } }
        val friendRequestIdsFlow = friendRequestRepository.getAllSent()
            .map {
                it.map { request -> request.receiverId }
            }

        return combine(
            usersFlow,
            friendIdsFlow,
            friendRequestIdsFlow
        ) { users, friendIds, friendRequestIds ->
            users.map { user ->
                FollowableUser(
                    id = user.id,
                    username = user.username,
                    email = user.email,
                    profilePictureUrl = user.profilePictureUrl,
                    status = extractFollowableUserStatus(user.id, friendIds, friendRequestIds)
                )
            }
        }
    }

    suspend fun sendFriendRequest(receiver: FollowableUser) {
        val sender = userRepository.getCurrentUser()
        friendRequestRepository.create(
            FriendRequest(
                senderId = sender.id,
                senderUsername = sender.username,
                senderEmail = sender.email,
                senderProfilePictureUrl = sender.profilePictureUrl,
                receiverId = receiver.id,
                receiverUsername = receiver.username,
                receiverEmail = receiver.email,
                receiverProfilePictureUrl = receiver.profilePictureUrl,
                status = FriendRequestStatus.Pending
            )
        )
    }

    private fun extractFollowableUserStatus(
        userId: String,
        friendIds: List<String>,
        friendRequestIds: List<String>
    ): FollowableUserStatus = when (userId) {
        in friendIds -> FollowableUserStatus.Friends
        in friendRequestIds -> FollowableUserStatus.Pending
        else -> FollowableUserStatus.Followable
    }
}