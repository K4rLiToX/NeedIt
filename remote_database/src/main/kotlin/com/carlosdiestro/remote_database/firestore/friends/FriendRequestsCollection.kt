package com.carlosdiestro.remote_database.firestore.friends

import com.carlosdiestro.remote_database.firestore.asFlow
import com.carlosdiestro.remote_database.firestore.asFriendRequestDto
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class FriendRequestsCollection @Inject constructor(
    private val requestsCollection: CollectionReference
) {

    fun create(request: FriendRequestDto) {
        requestsCollection
            .document(request.combinedId)
            .set(request)
    }

    fun delete(request: FriendRequestDto) {
        requestsCollection
            .document(request.combinedId)
            .delete()

    }

    fun getAllReceived(receiverId: String): Flow<List<FriendRequestDto>> = requestsCollection
        .whereEqualTo("receiverId", receiverId)
        .asFlow()
        .asFriendRequestDto()
}