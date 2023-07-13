package com.carlosdiestro.needit.domain.wishes

import javax.inject.Inject

class LockWishUseCase @Inject constructor(
    private val repository: WishRepository
) {
    suspend operator fun invoke(id: Long) = repository.lockWish(id)
}