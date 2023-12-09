package com.carlosdiestro.remote_database.firestore.friends

data class FriendDto(
    val id: String,
    val username: String,
    val email: String,
    val profilePictureUrl: String
) {
    constructor() : this("", "", "", "")
}