package com.carlosdiestro.needit.domain.wishes

import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory

sealed class Wish {
    abstract val id: Long
    abstract val imageUrl: String
    abstract val price: Double
    abstract val description: String
    abstract val webUrl: String
    abstract val isShared: Boolean
    abstract val category: WishCategory
    abstract val title: String
    abstract val subtitle: String
}

class Clothes(
    override val id: Long,
    override val imageUrl: String,
    override val price: Double,
    override val description: String,
    override val webUrl: String,
    override val isShared: Boolean,
    override val category: WishCategory = WishCategory.Clothes,
    override val title: String,
    override val subtitle: String,
    val size: String,
    val color: String
) : Wish()

class Footwear(
    override val id: Long,
    override val imageUrl: String,
    override val price: Double,
    override val description: String,
    override val webUrl: String,
    override val isShared: Boolean,
    override val category: WishCategory = WishCategory.Footwear,
    override val title: String,
    override val subtitle: String,
    val size: Int,
    val color: String
) : Wish()

class Book(
    override val id: Long,
    override val imageUrl: String,
    override val price: Double,
    override val description: String,
    override val webUrl: String,
    override val isShared: Boolean,
    override val category: WishCategory = WishCategory.Books,
    override val title: String,
    override val subtitle: String,
    val isbn: String
) : Wish()

class Other(
    override val id: Long,
    override val imageUrl: String,
    override val price: Double,
    override val description: String,
    override val webUrl: String,
    override val isShared: Boolean,
    override val category: WishCategory,
    override val title: String,
    override val subtitle: String
) : Wish()

sealed class WishParams

class ClothesParams(
    val size: String,
    val color: String
) : WishParams()

class FootwearParams(
    val size: Int,
    val color: String
) : WishParams()

class BookParams(
    val isbn: String
) : WishParams()

class OtherParams(
    val category: WishCategory?
) : WishParams()

object WishFactory {
    var id: Long = -1
    lateinit var imageUrl: String
    var price: Double = 0.0
    lateinit var description: String
    lateinit var webUrl: String
    var isShared: Boolean = false
    lateinit var title: String
    lateinit var subtitle: String

    fun initialize(
        id: Long = -1,
        imageUrl: String,
        price: Double,
        description: String,
        webUrl: String,
        isShared: Boolean = false,
        title: String,
        subtitle: String
    ): WishFactory = WishFactory.apply {
        this.id = id
        this.imageUrl = imageUrl
        this.price = price
        this.description = description
        this.webUrl = webUrl
        this.isShared = isShared
        this.title = title
        this.subtitle = subtitle
    }

    inline fun <reified T> create(params: WishParams): T = when (params) {
        is ClothesParams -> Clothes(
            id = id,
            imageUrl = imageUrl,
            price = price,
            description = description,
            webUrl = webUrl,
            isShared = isShared,
            category = WishCategory.Clothes,
            title = title,
            subtitle = subtitle,
            size = params.size,
            color = params.color
        )

        is FootwearParams -> Footwear(
            id = id,
            imageUrl = imageUrl,
            price = price,
            description = description,
            webUrl = webUrl,
            isShared = isShared,
            category = WishCategory.Footwear,
            title = title,
            subtitle = subtitle,
            size = params.size,
            color = params.color
        )

        is BookParams -> Book(
            id = id,
            imageUrl = imageUrl,
            price = price,
            description = description,
            webUrl = webUrl,
            isShared = isShared,
            category = WishCategory.Books,
            title = title,
            subtitle = subtitle,
            isbn = params.isbn
        )

        is OtherParams -> Other(
            id = id,
            imageUrl = imageUrl,
            price = price,
            description = description,
            webUrl = webUrl,
            isShared = isShared,
            category = params.category!!,
            title = title,
            subtitle = subtitle
        )
    } as T
}