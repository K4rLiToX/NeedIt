package com.carlosdiestro.local_database.friend_requests

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "friend_request_table",
    primaryKeys = ["sender_id", "receiver_id"]
)
data class FriendRequestEntity(
    @ColumnInfo(name = "sender_id")
    val senderId: String,
    @ColumnInfo(name = "sender_username")
    val senderUsername: String,
    @ColumnInfo(name = "sender_email")
    val senderEmail: String,
    @ColumnInfo(name = "sender_profile_picture_url")
    val senderProfilePictureUrl: String,
    @ColumnInfo(name = "receiver_id")
    val receiverId: String,
    @ColumnInfo(name = "receiver_username")
    val receiverUsername: String,
    @ColumnInfo(name = "receiver_email")
    val receiverEmail: String,
    @ColumnInfo(name = "receiver_profile_picture_url")
    val receiverProfilePictureUrl: String
)