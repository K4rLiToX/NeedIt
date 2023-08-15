package com.carlosdiestro.needit.domain.wishes

import com.carlosdiestro.needit.data.wishes.repository.WishRepository
import javax.inject.Inject

class GetWishUseCase @Inject constructor(
    private val repository: WishRepository
) {
    suspend operator fun invoke(id: Long): Wish = repository.getWish(id)
}