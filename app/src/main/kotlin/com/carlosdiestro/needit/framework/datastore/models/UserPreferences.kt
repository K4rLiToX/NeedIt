package com.carlosdiestro.needit.framework.datastore.models

data class UserPreferences(
    val id: String,
    val username: String,
    val email: String,
    val profilePictureUrl: String,
    val isAnonymous: Boolean
)