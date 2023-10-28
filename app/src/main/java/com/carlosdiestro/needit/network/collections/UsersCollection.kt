package com.carlosdiestro.needit.network.collections

import com.carlosdiestro.needit.network.dtos.UserDto
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import javax.inject.Inject

class UsersCollection @Inject constructor(
    private val usersCollection: CollectionReference
) {

    suspend fun upsert(dto: UserDto) {
        usersCollection
            .document(dto.id)
            .set(dto)
    }

    suspend fun update(dto: UserDto) {
        usersCollection
            .document(dto.id)
            .set(dto, SetOptions.merge())
    }
}