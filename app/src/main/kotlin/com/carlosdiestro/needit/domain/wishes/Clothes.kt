package com.carlosdiestro.needit.domain.wishes

import java.util.UUID

class Clothes private constructor(
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
    override val subtitle: String,
    val size: String,
    val color: String
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
        subtitle: String = this.subtitle,
        size: String = this.size,
        color: String = this.color,
    ): Clothes = Clothes(
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
    )

    companion object {
        fun create(
            args: WishInformation,
            size: String,
            color: String
        ): Clothes = Clothes(
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
            subtitle = args.subtitle,
            size = size,
            color = color,
        )
    }
}