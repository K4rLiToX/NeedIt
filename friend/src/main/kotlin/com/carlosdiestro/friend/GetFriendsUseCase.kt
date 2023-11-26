package com.carlosdiestro.friend

import com.carlosdiestro.friend.domain.Friend
import com.carlosdiestro.friend.domain.FriendRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFriendsUseCase @Inject constructor(
    private val repository: FriendRepository
) {

    operator fun invoke(): Flow<List<Friend>> = repository.getAll()
}