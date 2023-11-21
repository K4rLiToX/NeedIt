package com.carlosdiestro.remotedatabase.storage

import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class ImagesCollection @Inject constructor(
    private val storage: FirebaseStorage
) {

    suspend fun create(bytes: ByteArray, userId: String): String {
        val name = System.currentTimeMillis()
        val ref = storage.reference.child("$userId/$name.jpg")

        return ref
            .putBytes(bytes)
            .await()
            .storage
            .downloadUrl
            .await()
            .toString()
    }

    fun delete(path: String) {
        storage.reference.child(path).delete()
    }
}