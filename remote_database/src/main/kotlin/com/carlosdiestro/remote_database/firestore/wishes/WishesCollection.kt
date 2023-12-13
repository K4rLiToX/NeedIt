package com.carlosdiestro.remote_database.firestore.wishes

import com.carlosdiestro.remote_database.firestore.asDto
import com.carlosdiestro.remote_database.firestore.asFlow
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class WishesCollection @Inject constructor(
    private val wishesCollection: CollectionReference
) {

    fun upsert(wish: WishDto) {
        wishesCollection
            .document(wish.id)
            .set(wish, SetOptions.merge())
    }

    fun delete(wish: WishDto) {
        wishesCollection
            .document(wish.id)
            .delete()
    }

    fun getUserWishes(userId: String): Flow<List<WishDto>> =
        wishesCollection
            .whereEqualTo("userId", userId)
            .snapshots()
            .asDto<WishDto>()

    fun getUserWish(wishId: String): Flow<WishDto?> =
        wishesCollection
            .document(wishId)
            .asFlow<WishDto>()
}