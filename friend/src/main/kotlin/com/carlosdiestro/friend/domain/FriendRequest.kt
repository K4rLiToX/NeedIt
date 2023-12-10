package com.carlosdiestro.friend.domain

data class FriendRequest(
    val senderId: String,
    val senderUsername: String,
    val senderEmail: String,
    val senderProfilePictureUrl: String,
    val receiverId: String,
    val receiverUsername: String,
    val receiverEmail: String,
    val receiverProfilePictureUrl: String,
    val status: FriendRequestStatus
)

enum class FriendRequestStatus {
    Pending,
    Accepted,
    Rejected;

    fun asIntValue(): Int = this.ordinal
}

fun Int.asFriendRequestStatus(): FriendRequestStatus = FriendRequestStatus.entries[this]