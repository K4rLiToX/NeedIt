package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory
import com.carlosdiestro.needit.domain.users.usecases.GetUserInfoUseCase
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UpdateWishUseCase @Inject constructor(
    private val repository: WishRepository,
    private val getUserInfo: GetUserInfoUseCase
) {

    suspend operator fun invoke(
        id: Long,
        cloudId: String,
        imageUrl: String,
        title: String,
        subtitle: String,
        price: String,
        webUrl: String,
        isShared: Boolean,
        description: String,
        category: WishCategory,
        size: String,
        color: String,
        isbn: String
    ) {
        val userId = getUserInfo().first().id
        val wish = Wish(
            id = id,
            cloudId = cloudId,
            userId = userId,
            imageLocalPath = "",
            imageUrl = imageUrl,
            title = title,
            subtitle = subtitle,
            price = if (price.isEmpty()) 0.0 else price.toDouble(),
            webUrl = webUrl,
            isShared = isShared,
            description = description,
            category = category,
            size = size,
            color = color,
            isbn = isbn
        )
        repository.upsertWish(wish)
    }
}
