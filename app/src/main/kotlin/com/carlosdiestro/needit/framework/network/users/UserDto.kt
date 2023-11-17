package com.carlosdiestro.needit.framework.network.users

data class UserDto(
    val id: String,
    val username: String,
    val email: String,
    val profilePictureUrl: String,
    val isAnonymous: Boolean
)
