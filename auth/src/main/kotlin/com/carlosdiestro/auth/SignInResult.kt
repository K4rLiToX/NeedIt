package com.carlosdiestro.auth

data class SignInResult(
    val data: UserAuth?,
    val errorMessage: String?,
    val isNewUser: Boolean
)

data class UserAuth(
    val userId: String,
    val username: String,
    val email: String,
    val profilePictureUrl: String?,
    val isAnonymous: Boolean
)
