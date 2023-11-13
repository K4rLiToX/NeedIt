package com.carlosdiestro.needit.device_storage

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.carlosdiestro.needit.data.wishes.datasources.FileManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class FileManagerImpl @Inject constructor(
    private val imageStorage: Uri,
    private val contentResolver: ContentResolver,
    private val ioDispatcher: CoroutineDispatcher
) : FileManager {

    override suspend fun getImageLocalPath(): String = withContext(ioDispatcher) {
        contentResolver.queryImagePath(imageStorage)
    }
}

private fun ContentResolver.queryImagePath(imageStorage: Uri): String {
    return this.query(
        imageStorage,
        arrayOf(MediaStore.Images.Media.DATA),
        null,
        null,
        null
    )?.use { cursor -> cursor.imagePath } ?: Uri.EMPTY.toString()
}

private val Cursor.imagePath: String
    get() = if (this.moveToLast()) {
        this.getString(
            this.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        )
    } else {
        Uri.EMPTY.toString()
    }

