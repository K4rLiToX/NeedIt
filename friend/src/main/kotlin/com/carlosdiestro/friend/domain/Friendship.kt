package com.carlosdiestro.friend.domain

data class Friendship(
    val user1Id: String,
    val user1Username: String,
    val user1Email: String,
    val user1ProfilePictureUrl: String,
    val user2Id: String,
    val user2Username: String,
    val user2Email: String,
    val user2ProfilePictureUrl: String
)