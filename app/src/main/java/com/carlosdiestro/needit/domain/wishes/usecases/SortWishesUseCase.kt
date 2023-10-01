package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.core.design_system.components.menus.SortType
import com.carlosdiestro.needit.domain.wishes.Wish
import javax.inject.Inject

class SortWishesUseCase @Inject constructor() {
    operator fun invoke(sortOption: SortType, wishes: List<Wish>): List<Wish> =
        when (sortOption) {
            SortType.HighToLow -> wishes.sortedByDescending { it.price }
            SortType.LowToHigh -> wishes.sortedBy { it.price }
        }
}