package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import javax.inject.Inject

class LockWishUseCase @Inject constructor(
    private val repository: WishRepository
) {
    suspend operator fun invoke(id: Long) = repository.lockWish(id)
}