package com.carlosdiestro.remote_database.firestore

import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal inline fun <reified T> QuerySnapshot?.asDto(): List<T> =
    this?.mapNotNull { it.toObject(T::class.java) } ?: emptyList()

internal inline fun <reified T> Flow<QuerySnapshot?>.asDto(): Flow<List<T>> =
    this.map { it.asDto() }

internal inline fun <reified T> DocumentReference.asFlow() = flow {
    val task = get()
    val result = runCatching { Tasks.await(task) }
    val data = result.getOrNull()?.toObject(T::class.java)

    emit(data)
}



