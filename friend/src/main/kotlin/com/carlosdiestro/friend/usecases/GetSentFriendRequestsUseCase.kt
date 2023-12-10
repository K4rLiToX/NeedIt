package com.carlosdiestro.friend.usecases

import com.carlosdiestro.friend.domain.FriendRequest
import com.carlosdiestro.friend.domain.FriendRequestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSentFriendRequestsUseCase @Inject constructor(
    private val repository: FriendRequestRepository
) {

    operator fun invoke(
        userId: String = "",
        remote: Boolean = false
    ): Flow<List<FriendRequest>> = repository.getAllSent(userId = userId, remote = remote)

    fun getIds(): Flow<List<String>> = repository.getAllSentIds()
}