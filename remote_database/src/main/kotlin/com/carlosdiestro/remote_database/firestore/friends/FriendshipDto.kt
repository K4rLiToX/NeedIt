package com.carlosdiestro.remote_database.firestore.friends

data class FriendshipDto(
    val user1Id: String,
    val user1Username: String,
    val user1Email: String,
    val user1ProfilePictureUrl: String,
    val user2Id: String,
    val user2Username: String,
    val user2Email: String,
    val user2ProfilePictureUrl: String
) {
    val combinedId: String
        get() = "$user1Id$user2Id"

    constructor() : this("", "", "", "", "", "", "", "")
}