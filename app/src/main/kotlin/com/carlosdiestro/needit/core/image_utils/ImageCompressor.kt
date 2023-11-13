package com.carlosdiestro.needit.core.image_utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import com.carlosdiestro.needit.core.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class ImageCompressor @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    suspend fun compress(path: String): ByteArray = withContext(defaultDispatcher) {
        path.toBitmap().compress()
    }

    private fun String.toBitmap(): Bitmap = BitmapFactory.decodeFile(this).rotate()

    private fun Bitmap.rotate(): Bitmap {
        val matrix = Matrix().apply { postRotate(90F) }
        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
    }

    private fun Bitmap.compress(): ByteArray {
        val baos = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        return baos.toByteArray()
    }
}