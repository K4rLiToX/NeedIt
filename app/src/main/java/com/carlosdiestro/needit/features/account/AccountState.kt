package com.carlosdiestro.needit.features.account

data class AccountDataState(
    val profilePictureUrl: String = "",
    val name: String = "",
    val email: String = "",
    val birthday: String = "",
    val currency: String = ""
)