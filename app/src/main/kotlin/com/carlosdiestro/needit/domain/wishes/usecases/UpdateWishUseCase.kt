package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import javax.inject.Inject

class UpdateWishUseCase @Inject constructor(
    private val repository: WishRepository
) {

    suspend operator fun invoke(wish: Wish) {
        repository.update(wish)
    }
}
