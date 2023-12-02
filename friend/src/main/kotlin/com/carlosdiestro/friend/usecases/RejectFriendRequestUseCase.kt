package com.carlosdiestro.friend.usecases

import com.carlosdiestro.friend.domain.FriendRequest
import com.carlosdiestro.friend.domain.FriendRequestRepository
import javax.inject.Inject

class RejectFriendRequestUseCase @Inject constructor(
    private val repository: FriendRequestRepository
) {

    suspend operator fun invoke(request: FriendRequest) = repository.delete(request)
}