package com.carlosdiestro.remote_database.firestore.friends

import com.carlosdiestro.remote_database.firestore.asFriendRequestDto
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class FriendRequestsCollection @Inject constructor(
    private val requestsCollection: CollectionReference
) {

    fun upsert(request: FriendRequestDto) {
        requestsCollection
            .document(request.combinedId)
            .set(request, SetOptions.merge())
    }

    fun delete(request: FriendRequestDto) {
        requestsCollection
            .document(request.combinedId)
            .delete()

    }

    fun getAllReceived(receiverId: String): Flow<List<FriendRequestDto>> = requestsCollection
        .whereEqualTo("receiverId", receiverId)
        .whereEqualTo("status", 0)
        .snapshots()
        .asFriendRequestDto()

    suspend fun getAllSent(senderId: String): List<FriendRequestDto> = requestsCollection
        .where(
            Filter.and(
                Filter.equalTo("senderId", senderId),
                Filter.or(
                    Filter.equalTo("status", 1),
                    Filter.equalTo("status", 2)
                )
            )
        )
        .get()
        .await()
        .asFriendRequestDto()
}