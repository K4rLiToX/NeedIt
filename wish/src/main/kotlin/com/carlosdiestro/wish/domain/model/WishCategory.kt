package com.carlosdiestro.wish.domain.model

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

fun Int.toWishCategory(): WishCategory = WishCategory.entries[this]