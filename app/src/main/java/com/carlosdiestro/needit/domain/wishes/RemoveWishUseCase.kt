package com.carlosdiestro.needit.domain.wishes

import javax.inject.Inject

class RemoveWishUseCase @Inject constructor(
    private val repository: WishRepository
) {
    suspend operator fun invoke(id: Long) = repository.removeWish(id)
}