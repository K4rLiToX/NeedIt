package com.carlosdiestro.remote_database.firestore.wishes

import com.carlosdiestro.remote_database.CollectionsPath
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import javax.inject.Inject

internal class WishesCollection @Inject constructor(
    private val usersCollection: CollectionReference
) {

    fun upsert(wish: WishDto) {
        usersCollection
            .document(wish.userId)
            .collection(CollectionsPath.userWishes)
            .document(wish.id)
            .set(wish, SetOptions.merge())
    }

    fun delete(cloudId: String, userId: String) {
        usersCollection
            .document(userId)
            .collection(CollectionsPath.userWishes)
            .document(cloudId)
            .delete()
    }
}