package com.carlosdiestro.needit.framework.network.users

import com.google.firebase.firestore.CollectionReference
import javax.inject.Inject

internal class UsersCollection @Inject constructor(
    private val usersCollection: CollectionReference
) {

    fun upsert(user: UserDto) {
        usersCollection
            .document(user.id)
            .set(user)
    }
}