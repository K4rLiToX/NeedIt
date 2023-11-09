package com.carlosdiestro.needit.files

import android.content.ContentResolver
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.carlosdiestro.needit.data.wishes.datasources.FileManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class FileManagerImpl @Inject constructor(
    private val contentResolver: ContentResolver,
    private val ioDispatcher: CoroutineDispatcher
) : FileManager {
    private val folder: Uri
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL
            )
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

    private val projection: Array<String>
        get() = arrayOf(
            MediaStore.Images.Media.DATA
        )

    override suspend fun getImageLocalPath(): String = withContext(ioDispatcher) {
        contentResolver.query(
            folder,
            projection,
            null,
            null,
            null
        )?.use { cursor ->
            val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            if (cursor.moveToLast()) {
                cursor.getString(dataColumn)
            } else {
                Uri.EMPTY.toString()
            }
        } ?: Uri.EMPTY.toString()
    }
}