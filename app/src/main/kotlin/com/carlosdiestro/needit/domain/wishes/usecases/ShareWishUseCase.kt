package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.domain.wishes.Book
import com.carlosdiestro.needit.domain.wishes.Clothes
import com.carlosdiestro.needit.domain.wishes.Footwear
import com.carlosdiestro.needit.domain.wishes.Other
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import javax.inject.Inject

class ShareWishUseCase @Inject constructor(
    private val repository: WishRepository
) {

    suspend operator fun invoke(wish: Wish) {
        repository.update(
            when (wish) {
                is Clothes -> wish.copy(isShared = true)
                is Footwear -> wish.copy(isShared = true)
                is Book -> wish.copy(isShared = true)
                is Other -> wish.copy(isShared = true)
            }
        )
    }
}