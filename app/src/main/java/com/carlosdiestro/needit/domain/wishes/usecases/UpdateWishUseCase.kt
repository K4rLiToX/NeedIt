package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory
import com.carlosdiestro.needit.domain.users.usecases.GetUserInfoUseCase
import com.carlosdiestro.needit.domain.wishes.Book
import com.carlosdiestro.needit.domain.wishes.BookParams
import com.carlosdiestro.needit.domain.wishes.Clothes
import com.carlosdiestro.needit.domain.wishes.ClothesParams
import com.carlosdiestro.needit.domain.wishes.Footwear
import com.carlosdiestro.needit.domain.wishes.FootwearParams
import com.carlosdiestro.needit.domain.wishes.Other
import com.carlosdiestro.needit.domain.wishes.OtherParams
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.WishFactory
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
        WishFactory.initialize(
            id = id,
            cloudId = cloudId,
            userId = userId,
            imageUrl = imageUrl,
            title = title,
            subtitle = subtitle,
            price = if (price.isEmpty()) 0.0 else price.toDouble(),
            webUrl = webUrl,
            isShared = isShared,
            description = description
        )
        val wish = when (category) {
            WishCategory.Clothes -> WishFactory.create<Clothes>(
                ClothesParams(
                    size = size,
                    color = color
                )
            )

            WishCategory.Footwear -> WishFactory.create<Footwear>(
                FootwearParams(
                    size = if (size.isEmpty()) 0 else size.toInt(),
                    color = color
                )
            )

            WishCategory.Books -> WishFactory.create<Book>(
                BookParams(isbn = isbn)
            )

            else -> WishFactory.create<Other>(
                OtherParams(category = category)
            )
        }
        repository.upsertWish(wish)
    }
}
