package com.carlosdiestro.friend.data

import com.carlosdiestro.friend.domain.Friendship
import com.carlosdiestro.friend.domain.FriendshipRepository
import javax.inject.Inject

class FriendshipRepositoryImpl @Inject constructor(
    private val remoteDatasource: FriendshipRemoteDatasource
) : FriendshipRepository {

    override fun create(friendship: Friendship) = remoteDatasource.create(friendship)
}