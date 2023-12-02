package com.carlosdiestro.friend.data

import com.carlosdiestro.friend.domain.Friendship

interface FriendshipRemoteDatasource {
    fun create(friendship: Friendship)
}