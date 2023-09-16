package com.carlosdiestro.needit.network.collections

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject

class ImagesCollection @Inject constructor(
    private val storage: FirebaseStorage
) {

    suspend fun insertImage(path: String, userId: String): String {
        val file = Uri.fromFile(
            File(path)
        )
        val ref = storage.reference.child("$userId/${file.lastPathSegment}")

        return ref
            .putFile(file)
            .await()
            .storage
            .downloadUrl
            .await()
            .toString()
    }
}