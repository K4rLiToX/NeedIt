package com.carlosdiestro.friend.usecases

import com.carlosdiestro.friend.domain.FriendRequestStatus
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SyncFriendsUseCase @Inject constructor(
    private val getSentFriendRequests: GetSentFriendRequestsUseCase,
    private val addFriend: AddFriendUseCase,
    private val removeFriendRequest: RemoveFriendRequestUseCase
) {

    suspend operator fun invoke(userId: String) {
        getSentFriendRequests(userId = userId, remote = true)
            .first()
            .forEach { request ->
                if (request.status == FriendRequestStatus.Accepted) addFriend(request)
                removeFriendRequest(request)
            }
    }
}