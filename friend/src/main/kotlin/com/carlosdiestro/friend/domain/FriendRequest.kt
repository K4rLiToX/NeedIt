package com.carlosdiestro.friend.domain

data class FriendRequest(
    val senderId: String,
    val senderUsername: String,
    val senderEmail: String,
    val senderProfilePictureUrl: String,
    val receiverId: String
)