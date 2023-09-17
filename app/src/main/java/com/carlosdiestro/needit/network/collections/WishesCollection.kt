package com.carlosdiestro.needit.network.collections

import com.carlosdiestro.needit.network.dtos.WishDto
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class WishesCollection @Inject constructor(
    private val userWishesCollection: CollectionReference
) {

    suspend fun insert(dto: WishDto): String {
        return userWishesCollection
            .add(dto)
            .await()
            .id
    }

    suspend fun delete(cloudId: String) {
        userWishesCollection.document(cloudId).delete()
    }
}