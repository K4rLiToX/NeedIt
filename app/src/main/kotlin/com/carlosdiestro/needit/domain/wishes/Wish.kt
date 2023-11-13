package com.carlosdiestro.needit.domain.wishes

import java.util.UUID

class Wish private constructor(
    val id: UUID,
    val userId: String,
    val imageLocalPath: String,
    val imageUrl: String,
    val price: Double,
    val description: String,
    val webUrl: String,
    val isShared: Boolean,
    val category: WishCategory,
    val title: String,
    val subtitle: String,
    val size: String?,
    val color: String?,
    val isbn: String?
) {
    fun copy(
        id: UUID = this.id,
        userId: String = this.userId,
        imageLocalPath: String = this.imageLocalPath,
        imageUrl: String = this.imageUrl,
        price: Double = this.price,
        description: String = this.description,
        webUrl: String = this.webUrl,
        isShared: Boolean = this.isShared,
        category: WishCategory = this.category,
        title: String = this.title,
        subtitle: String = this.subtitle,
        size: String? = this.size,
        color: String? = this.color,
        isbn: String? = this.isbn
    ): Wish = Wish(
        id = id,
        userId = userId,
        imageLocalPath = imageLocalPath,
        imageUrl = imageUrl,
        price = price,
        description = description,
        webUrl = webUrl,
        isShared = isShared,
        category = category,
        title = title,
        subtitle = subtitle,
        size = size,
        color = color,
        isbn = isbn
    )

    companion object {
        fun create(
            id: UUID = UUID.randomUUID(),
            userId: String,
            imageLocalPath: String,
            imageUrl: String = "",
            price: Double,
            description: String,
            webUrl: String,
            isShared: Boolean = false,
            category: WishCategory,
            title: String,
            subtitle: String,
            size: String?,
            color: String?,
            isbn: String?
        ): Wish = Wish(
            id = id,
            userId = userId,
            imageLocalPath = imageLocalPath,
            imageUrl = imageUrl,
            title = title,
            subtitle = subtitle,
            price = price,
            webUrl = webUrl,
            description = description,
            category = category,
            isShared = isShared,
            size = size,
            color = color,
            isbn = isbn
        )
    }
}