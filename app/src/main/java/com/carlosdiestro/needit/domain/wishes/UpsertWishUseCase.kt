package com.carlosdiestro.needit.domain.wishes

import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory
import javax.inject.Inject

class UpsertWishUseCase @Inject constructor(
    private val repository: WishRepository
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
        val wishFactory = WishFactory.initialize(
            id = id,
            imageUrl = imageUrl,
            title = title,
            subtitle = subtitle,
            price = if (price.isEmpty()) 0.0 else price.toDouble(),
            webUrl = webUrl,
            description = description
        )
        val wish = when (category) {
            WishCategory.Clothes -> wishFactory.create<Clothes>(
                ClothesParams(
                    size = size,
                    color = color
                )
            )

            WishCategory.Footwear -> wishFactory.create<Footwear>(
                FootwearParams(
                    size = if (size.isEmpty()) 0 else size.toInt(),
                    color = color
                )
            )

            WishCategory.Books -> wishFactory.create<Book>(
                BookParams(isbn = isbn)
            )

            else -> wishFactory.create<Other>(
                OtherParams(category = category)
            )
        }
        repository.upsertWish(wish)
    }
}