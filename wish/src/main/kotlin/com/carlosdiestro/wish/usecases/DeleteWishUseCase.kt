package com.carlosdiestro.wish.usecases

import com.carlosdiestro.wish.domain.model.Wish
import com.carlosdiestro.wish.domain.repository.ImageRepository
import com.carlosdiestro.wish.domain.repository.WishRepository
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
        .split("/")
        .last()
}