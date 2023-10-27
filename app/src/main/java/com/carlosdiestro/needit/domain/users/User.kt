package com.carlosdiestro.needit.domain.users

class User(
    val id: String,
    val username: String,
    val email: String,
    val profilePictureUrl: String,
    val isAnonymous: Boolean
)