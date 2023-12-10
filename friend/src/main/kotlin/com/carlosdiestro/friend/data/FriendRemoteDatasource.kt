package com.carlosdiestro.friend.data

import com.carlosdiestro.friend.domain.Friend

interface FriendRemoteDatasource {
    fun create(userId: String, friend: Friend)
}