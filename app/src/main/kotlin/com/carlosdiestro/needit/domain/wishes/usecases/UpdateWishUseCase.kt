package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.domain.wishes.Book
import com.carlosdiestro.needit.domain.wishes.Clothes
import com.carlosdiestro.needit.domain.wishes.Footwear
import com.carlosdiestro.needit.domain.wishes.Other
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
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
