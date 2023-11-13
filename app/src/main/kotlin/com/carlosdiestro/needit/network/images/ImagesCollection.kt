package com.carlosdiestro.needit.network.images

import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class ImagesCollection @Inject constructor(
    private val storage: FirebaseStorage,
    private val imageCompressor: ImageCompressor
) {

    suspend fun create(imageLocalPath: String, userId: String): String {
        val name = System.currentTimeMillis()
        val ref = storage.reference.child("$userId/$name.jpg")
        val compressedImage = imageCompressor.compress(imageLocalPath)

        return ref
            .putBytes(compressedImage)
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