package com.carlosdiestro.needit.domain.users

data class User(
    val id: String,
    val username: String,
    val email: String,
    val profilePictureUrl: String,
    val isAnonymous: Boolean
)