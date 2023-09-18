package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory
import com.carlosdiestro.needit.domain.users.usecases.GetUserInfoUseCase
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.repository.ImageRepository
import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class InsertWishUseCase @Inject constructor(
    private val wishRepository: WishRepository,
    private val imageRepository: ImageRepository,
    private val getUserInfo: GetUserInfoUseCase
) {
    suspend operator fun invoke(
        id: Long,
        imageUrl: String,
        title: String,
        subtitle: String,
        price: String,
        webUrl: String,
        description: String,
        category: WishCategory,
        size: String,
        color: String,
        isbn: String
    ) {
        val userId = getUserInfo().first().id
        val cloudImageUrl = imageRepository.insertImage(imageUrl, userId)
        val wish = Wish(
            id = id,
            cloudId = "",
            userId = userId,
            imageLocalPath = imageUrl,
            imageUrl = cloudImageUrl,
            title = title,
            subtitle = subtitle,
            price = if (price.isEmpty()) 0.0 else price.toDouble(),
            webUrl = webUrl,
            description = description,
            category = category,
            isShared = false,
            size = size,
            color = color,
            isbn = isbn
        )
        wishRepository.upsertWish(wish)
    }
}