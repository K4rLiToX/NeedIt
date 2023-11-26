package com.carlosdiestro.friend.domain

data class Friend(
    val id: String,
    val username: String,
    val email: String,
    val profilePictureUrl: String,
    val friendStatus: FriendStatus
)

enum class FriendStatus {
    Friends,
    Pending;

    fun asIntValue(): Int = this.ordinal
}

fun Int.asFriendStatus(): FriendStatus = FriendStatus.entries[this]