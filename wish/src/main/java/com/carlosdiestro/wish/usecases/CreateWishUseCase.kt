package com.carlosdiestro.wish.usecases

import com.carlosdiestro.wish.domain.model.Wish
import com.carlosdiestro.wish.domain.model.WishCategory
import com.carlosdiestro.wish.domain.model.WishInformation
import com.carlosdiestro.wish.domain.repository.ImageRepository
import com.carlosdiestro.wish.domain.repository.WishRepository
import javax.inject.Inject

class CreateWishUseCase @Inject constructor(
    private val wishRepository: WishRepository,
    private val imageRepository: ImageRepository
) {
    suspend operator fun invoke(
        args: WishInformation,
        compressedImage: ByteArray,
        size: String?,
        color: String?,
        isbn: String?
    ) {
        val cloudImageUrl = imageRepository.create(compressedImage, args.userId)
        val info = args.copy(imageUrl = cloudImageUrl)
        val wish = when (args.category) {
            WishCategory.Clothes -> Wish.createClothes(info, size ?: "", color ?: "")
            WishCategory.Footwear -> Wish.createFootwear(info, size ?: "", color ?: "")
            WishCategory.Books -> Wish.createBook(info, isbn ?: "")
            WishCategory.Accessories, WishCategory.Grooming, WishCategory.Tech, WishCategory
                .Other -> Wish.createOther(info)
        }
        wishRepository.create(wish)
    }
}