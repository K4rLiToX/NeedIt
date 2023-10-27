package com.carlosdiestro.needit.auth

data class SignInResult(
    val data: UserAuth?,
    val errorMessage: String?
)

data class UserAuth(
    val userId: String,
    val username: String,
    val email: String,
    val profilePictureUrl: String?,
    val isAnonymous: Boolean
)
