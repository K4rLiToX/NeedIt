package com.carlosdiestro.needit.domain.wishes

enum class WishCategory {
    Clothes,
    Footwear,
    Accessories,
    Grooming,
    Books,
    Tech,
    Other;

    fun toIntValue(): Int = this.ordinal
}

fun Int.toWishCategory(): WishCategory = WishCategory.values()[this]