package com.carlosdiestro.needit.network

import com.carlosdiestro.needit.network.dtos.UserDto
import com.google.firebase.firestore.CollectionReference
import javax.inject.Inject

class UsersCollection @Inject constructor(
    private val usersCollection: CollectionReference
) {
    suspend fun insert(dto: UserDto) {
        usersCollection
            .document(dto.id)
            .set(dto)
    }
}