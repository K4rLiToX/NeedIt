package com.carlosdiestro.friend.domain

interface FriendshipRepository {

    fun create(friendship: Friendship)
}