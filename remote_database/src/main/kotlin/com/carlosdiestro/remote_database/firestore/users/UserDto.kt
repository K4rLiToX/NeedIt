package com.carlosdiestro.remote_database.firestore.users

data class UserDto(
    val id: String,
    val username: String,
    val email: String,
    val profilePictureUrl: String,
    val isAnonymous: Boolean
)
