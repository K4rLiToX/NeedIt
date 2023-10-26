package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.domain.wishes.repository.ImageRepository
import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import okhttp3.HttpUrl.Companion.toHttpUrl
import javax.inject.Inject

class RemoveWishUseCase @Inject constructor(
    private val wishRepository: WishRepository,
    private val imageRepository: ImageRepository
) {
    suspend operator fun invoke(
        id: Long,
        cloudId: String,
        imageUrl: String
    ) {
        wishRepository.removeWish(id, cloudId)
        imageRepository.deleteImage(getPath(imageUrl))
    }

    private fun getPath(imageUrl: String): String = imageUrl
        .toHttpUrl()
        .pathSegments
        .last()
}