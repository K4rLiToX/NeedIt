package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.core.di.ApplicationScope
import com.carlosdiestro.needit.domain.users.usecases.GetSignedInUserUseCase
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.WishCategory
import com.carlosdiestro.needit.domain.wishes.WishInformation
import com.carlosdiestro.needit.domain.wishes.repository.ImageRepository
import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateWishUseCase @Inject constructor(
    private val wishRepository: WishRepository,
    private val imageRepository: ImageRepository,
    private val getUserInfo: GetSignedInUserUseCase,
    @ApplicationScope private val scope: CoroutineScope
) {
    suspend operator fun invoke(
        args: WishInformation,
        compressedImage: ByteArray,
        size: String?,
        color: String?,
        isbn: String?
    ) {
        scope.launch {
            val userId = getUserInfo().first().id
            val cloudImageUrl = imageRepository.create(compressedImage, userId)
            val info = args.copy(userId = userId, imageUrl = cloudImageUrl)
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
}