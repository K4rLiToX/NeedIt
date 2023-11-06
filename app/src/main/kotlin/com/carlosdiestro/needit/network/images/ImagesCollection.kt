package com.carlosdiestro.needit.network.images

import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class ImagesCollection @Inject constructor(
    private val storage: FirebaseStorage
) {

    suspend fun insertImage(bytes: ByteArray, userId: String): String {
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

    fun deleteImage(path: String) {
        storage.reference.child(path).delete()
    }
}