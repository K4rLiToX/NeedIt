package com.carlosdiestro.friend.usecases

import com.carlosdiestro.friend.domain.FriendRequest
import com.carlosdiestro.friend.domain.FriendRequestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetReceivedFriendRequestsUseCase @Inject constructor(
    private val repository: FriendRequestRepository
) {

    operator fun invoke(userId: String): Flow<List<FriendRequest>> =
        repository.getAllReceived(userId)
}