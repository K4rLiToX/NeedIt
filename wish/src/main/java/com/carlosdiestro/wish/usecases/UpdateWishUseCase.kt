package com.carlosdiestro.wish.usecases

import com.carlosdiestro.wish.domain.model.Book
import com.carlosdiestro.wish.domain.model.Clothes
import com.carlosdiestro.wish.domain.model.Footwear
import com.carlosdiestro.wish.domain.model.Other
import com.carlosdiestro.wish.domain.model.Wish
import com.carlosdiestro.wish.domain.repository.WishRepository
import javax.inject.Inject

class UpdateWishUseCase @Inject constructor(
    private val repository: WishRepository
) {

    suspend operator fun invoke(
        wish: Wish,
        price: Double,
        description: String,
        webUrl: String,
        title: String,
        subtitle: String,
        size: String?,
        color: String?,
        isbn: String?
    ) {

        repository.update(
            when (wish) {
                is Clothes -> wish.copy(
                    price = price,
                    description = description,
                    webUrl = webUrl,
                    title = title,
                    subtitle = subtitle,
                    size = size ?: "",
                    color = color ?: ""
                )

                is Footwear -> wish.copy(
                    price = price,
                    description = description,
                    webUrl = webUrl,
                    title = title,
                    subtitle = subtitle,
                    size = size ?: "",
                    color = color ?: ""
                )

                is Book -> wish.copy(
                    price = price,
                    description = description,
                    webUrl = webUrl,
                    title = title,
                    subtitle = subtitle,
                    isbn = isbn ?: ""
                )

                is Other -> wish.copy(
                    price = price,
                    description = description,
                    webUrl = webUrl,
                    title = title,
                    subtitle = subtitle
                )
            }
        )
    }
}
