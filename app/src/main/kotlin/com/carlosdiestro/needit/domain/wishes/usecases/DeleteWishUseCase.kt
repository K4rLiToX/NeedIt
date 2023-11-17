package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.repository.ImageRepository
import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import okhttp3.HttpUrl.Companion.toHttpUrl
import javax.inject.Inject

class DeleteWishUseCase @Inject constructor(
    private val wishRepository: WishRepository,
    private val imageRepository: ImageRepository
) {
    suspend operator fun invoke(wish: Wish) {
        wishRepository.delete(wish)
        imageRepository.delete(getPath(wish.imageUrl))
    }

    private fun getPath(imageUrl: String): String = imageUrl
        .toHttpUrl()
        .pathSegments
        .last()
}