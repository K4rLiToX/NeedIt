package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyWishesUseCase @Inject constructor(
    private val repository: WishRepository
) {
    operator fun invoke(onlyShared: Boolean = false): Flow<List<Wish>> =
        if (onlyShared) repository.sharedWishes else repository.wishes
}