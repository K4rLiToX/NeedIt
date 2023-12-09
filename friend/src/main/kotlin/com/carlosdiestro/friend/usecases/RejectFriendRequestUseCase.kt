package com.carlosdiestro.friend.usecases

import com.carlosdiestro.friend.domain.FriendRequest
import com.carlosdiestro.friend.domain.FriendRequestRepository
import com.carlosdiestro.friend.domain.FriendRequestStatus
import javax.inject.Inject

class RejectFriendRequestUseCase @Inject constructor(
    private val repository: FriendRequestRepository
) {

    suspend operator fun invoke(request: FriendRequest) {
        repository.update(
            request.copy(status = FriendRequestStatus.Rejected)
        )
    }
}