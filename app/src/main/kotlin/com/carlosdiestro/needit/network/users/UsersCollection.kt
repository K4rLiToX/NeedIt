package com.carlosdiestro.needit.network.users

import com.google.firebase.firestore.CollectionReference
import javax.inject.Inject

class UsersCollection @Inject constructor(
    private val usersCollection: CollectionReference
) {

    fun upsert(dto: UserDto) {
        usersCollection
            .document(dto.id)
            .set(dto)
    }
}