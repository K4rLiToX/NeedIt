package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.domain.wishes.repository.ImageRepository
import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import okhttp3.HttpUrl.Companion.toHttpUrl
import javax.inject.Inject

class RemoveWishUseCase @Inject constructor(
    private val wishRepository: WishRepository,
    private val imageRepository: ImageRepository,
    private val getWish: GetWishUseCase
) {
    suspend operator fun invoke(id: Long) {
        val wish = getWish(id)
        wishRepository.removeWish(id, wish.cloudId)
        imageRepository.deleteImage(getPath(wish.imageUrl))
    }

    private fun getPath(imageUrl: String): String = imageUrl
        .toHttpUrl()
        .pathSegments
        .last()
}