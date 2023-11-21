package com.carlosdiestro.wish.usecases

import com.carlosdiestro.wish.domain.model.Wish
import com.carlosdiestro.wish.domain.repository.WishRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyWishesUseCase @Inject constructor(
    private val repository: WishRepository
) {
    operator fun invoke(onlyShared: Boolean = false): Flow<List<Wish>> =
        if (onlyShared) repository.sharedWishes else repository.wishes
}