package com.carlosdiestro.remote_database.firestore.friends

data class FriendRequestDto(
    val senderId: String,
    val senderUsername: String,
    val senderEmail: String,
    val senderProfilePictureUrl: String,
    val receiverId: String,
    val receiverUsername: String,
    val receiverEmail: String,
    val receiverProfilePictureUrl: String,
    val status: Int
) {
    val combinedId: String
        get() = "$receiverId$senderId"

    constructor() : this("", "", "", "", "", "", "", "", 0)
}