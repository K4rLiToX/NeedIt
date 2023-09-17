package com.carlosdiestro.needit.network.collections

import com.carlosdiestro.needit.network.dtos.WishDto
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class WishesCollection @Inject constructor(
    private val userWishesCollection: CollectionReference
) {

    suspend fun insert(wish: WishDto): String {
        return userWishesCollection
            .add(wish)
            .await()
            .id
    }

    suspend fun delete(cloudId: String) {
        userWishesCollection
            .document(cloudId
            ).delete()
    }

    suspend fun update(cloudId: String, wish: WishDto) {
        userWishesCollection
            .document(cloudId)
            .set(wish, SetOptions.merge())
    }
}