package com.carlosdiestro.feature.notifications

import com.carlosdiestro.friend.domain.FriendRequest
import com.carlosdiestro.friend.usecases.GetReceivedFriendRequestsUseCase
import com.carlosdiestro.user.usecases.GetSignedInUserUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NotificationsService @Inject constructor(
    private val getReceivedFriendRequests: GetReceivedFriendRequestsUseCase,
    private val getSignedInUser: GetSignedInUserUseCase
) {

    operator fun invoke(): Flow<List<FriendRequest>> = flow {
        getSignedInUser().collect {
            getReceivedFriendRequests(it.id).collect { requests ->
                emit(requests)
            }
        }
    }
}