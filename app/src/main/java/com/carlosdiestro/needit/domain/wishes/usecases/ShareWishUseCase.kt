package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import javax.inject.Inject

class ShareWishUseCase @Inject constructor(
    private val repository: WishRepository
) {
    suspend operator fun invoke(id: Long) = repository.shareWish(id)
}