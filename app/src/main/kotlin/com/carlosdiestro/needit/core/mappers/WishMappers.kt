package com.carlosdiestro.needit.core.mappers

import com.carlosdiestro.design_system.lists.HomeWishPlo
import com.carlosdiestro.design_system.lists.WishCategoryPlo
import com.carlosdiestro.wish.domain.model.Wish
import com.carlosdiestro.wish.domain.model.WishCategory


fun Wish.asPlo(): HomeWishPlo = HomeWishPlo(
    id = this.id.toString(),
    imageUrl = this.imageLocalPath.ifEmpty { this.imageUrl },
    shared = this.isShared,
    category = this.category.asPlo()
)

fun WishCategory.asPlo(): WishCategoryPlo = WishCategoryPlo.entries[this.ordinal]
fun WishCategoryPlo.asDomain(): WishCategory = WishCategory.entries[this.ordinal]

fun List<Wish>.asPlo(): List<HomeWishPlo> = this.map { it.asPlo() }


