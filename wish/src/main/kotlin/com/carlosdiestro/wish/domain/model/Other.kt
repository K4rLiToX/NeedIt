package com.carlosdiestro.wish.domain.model

import java.util.UUID

class Other private constructor(
    override val id: UUID,
    override val userId: String,
    override val imageLocalPath: String,
    override val imageUrl: String,
    override val price: Double,
    override val description: String,
    override val webUrl: String,
    override val isShared: Boolean,
    override val category: WishCategory,
    override val title: String,
    override val subtitle: String
) : Wish {
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
        subtitle: String = this.subtitle
    ): Other = Other(
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
        subtitle = subtitle
    )

    companion object {
        fun create(
            args: WishInformation
        ): Other = Other(
            id = args.id,
            userId = args.userId,
            imageLocalPath = args.imageLocalPath,
            imageUrl = args.imageUrl,
            price = args.price,
            description = args.description,
            webUrl = args.webUrl,
            isShared = args.isShared,
            category = args.category,
            title = args.title,
            subtitle = args.subtitle
        )
    }
}