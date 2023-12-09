package com.carlosdiestro.friend.usecases

import com.carlosdiestro.friend.domain.FriendRequest
import com.carlosdiestro.friend.domain.FriendRequestRepository
import javax.inject.Inject

class RemoveFriendRequestUseCase @Inject constructor(
    private val friendRequestRepository: FriendRequestRepository
){

    suspend operator fun invoke(request: FriendRequest) = friendRequestRepository.delete(request)
}