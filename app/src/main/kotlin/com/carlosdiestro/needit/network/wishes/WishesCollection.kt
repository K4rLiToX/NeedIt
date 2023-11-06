package com.carlosdiestro.needit.network.wishes

import com.carlosdiestro.needit.network.CollectionsPath
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WishesCollection @Inject constructor(
    private val usersCollection: CollectionReference,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun insert(wish: WishDto): String =
        usersCollection
            .document(wish.userId)
            .collection(CollectionsPath.userWishes)
            .add(wish)
            .await()
            .id

    suspend fun delete(cloudId: String, userId: String) {
            usersCollection
                .document(userId)
                .collection(CollectionsPath.userWishes)
                .document(cloudId).delete()
    }

    suspend fun update(cloudId: String, wish: WishDto) {
            usersCollection
                .document(wish.userId)
                .collection(CollectionsPath.userWishes)
                .document(cloudId)
                .set(wish, SetOptions.merge())
    }
}