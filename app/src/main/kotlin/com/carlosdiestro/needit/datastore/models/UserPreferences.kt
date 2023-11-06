package com.carlosdiestro.needit.datastore.models

class UserPreferences(
    val id: String,
    val username: String,
    val email: String,
    val profilePictureUrl: String,
    val isAnonymous: Boolean
)