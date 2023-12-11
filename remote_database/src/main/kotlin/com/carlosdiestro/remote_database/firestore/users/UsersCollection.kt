package com.carlosdiestro.remote_database.firestore.users

import com.carlosdiestro.remote_database.firestore.asUserDto
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class UsersCollection @Inject constructor(
    private val usersCollection: CollectionReference
) {

    fun upsert(user: UserDto) {
        usersCollection
            .document(user.id)
            .set(user)
    }

    fun getAll(): Flow<List<UserDto>> = usersCollection
        .whereEqualTo("anonymous", false)
        .snapshots()
        .asUserDto()
}