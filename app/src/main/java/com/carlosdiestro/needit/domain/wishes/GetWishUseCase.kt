package com.carlosdiestro.needit.domain.wishes

import javax.inject.Inject

class GetWishUseCase @Inject constructor(
    private val repository: WishRepository
) {
    suspend operator fun invoke(id: Long): Wish = repository.getWish(id)
}