package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWishUseCase @Inject constructor(
    private val repository: WishRepository
) {
    operator fun invoke(id: Long): Flow<Wish> = repository.getWish(id)
}