package com.carlosdiestro.needit.domain.wishes

import java.util.UUID

sealed interface Wish {
    val id: UUID
    val userId: String
    val imageLocalPath: String
    val imageUrl: String
    val price: Double
    val description: String
    val webUrl: String
    val isShared: Boolean
    val category: WishCategory
    val title: String
    val subtitle: String

    companion object Factory {
        fun createClothes(
            args: WishInformation,
            size: String,
            color: String
        ): Wish = Clothes.create(args, size, color)

        fun createBook(
            args: WishInformation,
            isbn: String
        ): Wish = Book.create(args, isbn)

        fun createFootwear(
            args: WishInformation,
            size: String,
            color: String
        ): Wish = Footwear.create(args, size, color)

        fun createOther(
            args: WishInformation
        ): Wish = Other.create(args)
    }
}

data class WishInformation(
    val id: UUID = UUID.randomUUID(),
    val userId: String = "",
    val imageLocalPath: String,
    val imageUrl: String = "",
    val price: Double,
    val description: String,
    val webUrl: String,
    val isShared: Boolean = false,
    val category: WishCategory,
    val title: String,
    val subtitle: String
)