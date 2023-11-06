package com.carlosdiestro.needit.domain.wishes.usecases

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import com.carlosdiestro.needit.core.design_system.components.lists.WishCategoryPlo
import com.carlosdiestro.needit.core.di.ApplicationScope
import com.carlosdiestro.needit.core.di.DefaultDispatcher
import com.carlosdiestro.needit.core.mappers.asDomain
import com.carlosdiestro.needit.domain.users.usecases.GetSignedInUserUseCase
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.repository.ImageRepository
import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class InsertWishUseCase @Inject constructor(
    private val wishRepository: WishRepository,
    private val imageRepository: ImageRepository,
    private val getUserInfo: GetSignedInUserUseCase,
    @ApplicationScope private val scope: CoroutineScope,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(
        imageLocalPath: String,
        title: String,
        subtitle: String,
        price: String,
        webUrl: String,
        description: String,
        category: WishCategoryPlo,
        size: String?,
        color: String?,
        isbn: String?
    ) {
        scope.launch {
            val userId = getUserInfo().first().id
            val wish = Wish(
                id = -1,
                cloudId = "",
                userId = userId,
                imageLocalPath = imageLocalPath,
                imageUrl = "",
                title = title,
                subtitle = subtitle,
                price = if (price.isEmpty()) 0.0 else price.toDouble(),
                webUrl = webUrl,
                description = description,
                category = category.asDomain(),
                isShared = false,
                size = size,
                color = color,
                isbn = isbn
            )
            val finalId = wishRepository.insertWish(wish)
            val compressedImage = compressImage(imageLocalPath)
            val cloudImageUrl = imageRepository.insertImage(compressedImage, userId)
            wishRepository.updateWish(
                wish.copy(id = finalId, imageUrl = cloudImageUrl)
            )
        }
    }

    private suspend fun compressImage(imageLocalPath: String): ByteArray =
        withContext(defaultDispatcher) {
            imageLocalPath.toBitmap().compress()
        }

    private fun String.toBitmap(): Bitmap = BitmapFactory.decodeFile(this).rotate()

    private fun Bitmap.compress(): ByteArray {
        val baos = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        return baos.toByteArray()
    }

    private fun Bitmap.rotate(): Bitmap {
        val matrix = Matrix().apply { postRotate(90F) }
        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
    }
}