package com.carlosdiestro.needit.domain.wishes

import com.carlosdiestro.needit.data.wishes.repository.WishRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyWishesUseCase @Inject constructor(
    private val repository: WishRepository
) {
    operator fun invoke(): Flow<List<Wish>> = repository.wishes
}