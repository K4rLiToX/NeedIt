package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.domain.wishes.repository.FileManagerRepository
import com.carlosdiestro.needit.domain.wishes.repository.ImageRepository
import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import okhttp3.HttpUrl.Companion.toHttpUrl
import javax.inject.Inject

class RemoveWishUseCase @Inject constructor(
    private val wishRepository: WishRepository,
    private val imageRepository: ImageRepository,
    private val fileManagerRepository: FileManagerRepository
) {
    suspend operator fun invoke(
        id: Long,
        cloudId: String,
        imageUrl: String,
        imageLocalPath: String
    ) {
        wishRepository.removeWish(id, cloudId)
        imageRepository.deleteImage(getPath(imageUrl))
        fileManagerRepository.delete(imageLocalPath)
    }

    private fun getPath(imageUrl: String): String = imageUrl
        .toHttpUrl()
        .pathSegments
        .last()
}