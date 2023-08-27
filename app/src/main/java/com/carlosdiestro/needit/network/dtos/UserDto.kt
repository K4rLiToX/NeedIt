package com.carlosdiestro.needit.network.dtos

data class UserDto(
    val id: String,
    val username: String,
    val email: String,
    val profilePictureUrl: String
)
