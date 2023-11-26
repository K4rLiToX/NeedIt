package com.carlosdiestro.friend.usecases

import com.carlosdiestro.friend.domain.FriendRequest
import com.carlosdiestro.friend.domain.FriendRequestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSentFriendRequestsUseCase @Inject constructor(
    private val repository: FriendRequestRepository
) {

    operator fun invoke(): Flow<List<FriendRequest>> = repository.getAllSent()
    fun getIds(): Flow<List<String>> = repository.getAllSentIds()
}