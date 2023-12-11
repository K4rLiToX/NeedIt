package com.carlosdiestro.remote_database.firestore

import com.carlosdiestro.remote_database.firestore.friends.FriendRequestDto
import com.carlosdiestro.remote_database.firestore.users.UserDto
import com.carlosdiestro.remote_database.firestore.wishes.WishDto
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal fun Query.asFlow(): Flow<QuerySnapshot?> = callbackFlow {
    val callback = addSnapshotListener { value, error ->
        if (error != null) close(error)
        else trySend(value)
    }
    awaitClose { callback.remove() }
}

internal fun QuerySnapshot?.asUserDto(): List<UserDto> =
    this?.mapNotNull { it.toObject(UserDto::class.java) } ?: emptyList()

internal fun QuerySnapshot?.asFriendRequestDto(): List<FriendRequestDto> =
    this?.mapNotNull { it.toObject(FriendRequestDto::class.java) } ?: emptyList()

internal fun QuerySnapshot?.asWishDto(): List<WishDto> =
    this?.mapNotNull { it.toObject(WishDto::class.java) } ?: emptyList()

internal fun Flow<QuerySnapshot?>.asUserDto(): Flow<List<UserDto>> = this.map { it.asUserDto() }

internal fun Flow<QuerySnapshot?>.asFriendRequestDto(): Flow<List<FriendRequestDto>> =
    this.map { it.asFriendRequestDto() }

internal fun Flow<QuerySnapshot?>.asWishDto(): Flow<List<WishDto>> =
    this.map { it.asWishDto() }

internal inline fun <reified T> DocumentReference.asFlow() = flow {
    val task = get()
    val result = runCatching { Tasks.await(task) }
    val data = result.getOrNull()?.toObject(T::class.java)

    emit(data)
}



