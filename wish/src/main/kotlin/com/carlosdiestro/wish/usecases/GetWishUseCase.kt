package com.carlosdiestro.wish.usecases

import com.carlosdiestro.wish.domain.model.Wish
import com.carlosdiestro.wish.domain.repository.WishRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWishUseCase @Inject constructor(
    private val repository: WishRepository
) {
    operator fun invoke(id: String): Flow<Wish> = repository.getWish(id)
}