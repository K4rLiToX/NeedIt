package com.carlosdiestro.wish.usecases

import com.carlosdiestro.wish.domain.model.Book
import com.carlosdiestro.wish.domain.model.Clothes
import com.carlosdiestro.wish.domain.model.Footwear
import com.carlosdiestro.wish.domain.model.Other
import com.carlosdiestro.wish.domain.model.Wish
import com.carlosdiestro.wish.domain.repository.WishRepository
import javax.inject.Inject

class LockWishUseCase @Inject constructor(
    private val repository: WishRepository
) {

    suspend operator fun invoke(wish: Wish) {
        repository.update(
            when (wish) {
                is Clothes -> wish.copy(isShared = false)
                is Footwear -> wish.copy(isShared = false)
                is Book -> wish.copy(isShared = false)
                is Other -> wish.copy(isShared = false)
            }
        )
    }
}