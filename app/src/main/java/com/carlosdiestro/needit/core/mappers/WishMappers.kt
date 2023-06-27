package com.carlosdiestro.needit.core.mappers

import com.carlosdiestro.needit.core.database.entities.WishEntity
import com.carlosdiestro.needit.core.design_system.components.cards.Currency
import com.carlosdiestro.needit.core.design_system.components.cards.SimpleWishPLO
import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory
import com.carlosdiestro.needit.core.design_system.components.navigation.toWishCategory
import com.carlosdiestro.needit.domain.wishes.Book
import com.carlosdiestro.needit.domain.wishes.BookParams
import com.carlosdiestro.needit.domain.wishes.Clothes
import com.carlosdiestro.needit.domain.wishes.ClothesParams
import com.carlosdiestro.needit.domain.wishes.Footwear
import com.carlosdiestro.needit.domain.wishes.FootwearParams
import com.carlosdiestro.needit.domain.wishes.Other
import com.carlosdiestro.needit.domain.wishes.OtherParams
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.WishFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun WishEntity.toDomain(): Wish {
    val factory = WishFactory
        .initialize(
            id = this.id ?: -1,
            imageUrl = this.imageUrl,
            price = this.price,
            description = this.description,
            webUrl = this.webUrl,
            isShared = this.isShared,
            title = this.title,
            subtitle = this.subtitle
        )
    return when (this.category.toWishCategory()) {
        WishCategory.Clothes -> factory.create<Clothes>(
            ClothesParams(
                this.size.orEmpty(),
                this.color.orEmpty()
            )
        )

        WishCategory.Footwear -> factory.create<Footwear>(
            FootwearParams(
                this.size?.toInt() ?: -1,
                this.color.orEmpty()
            )
        )

        WishCategory.Books -> factory.create<Book>(
            BookParams(
                this.isbn.orEmpty()
            )
        )

        else -> factory.create<Other>(
            OtherParams(
                this.category.toWishCategory()
            )
        )
    }
}

fun List<WishEntity>.toDomain(): List<Wish> = this.map { it.toDomain() }
fun Flow<List<WishEntity>>.toDomain(): Flow<List<Wish>> = this.map { it.toDomain() }

fun Wish.toPLO(): SimpleWishPLO = SimpleWishPLO(
    id = this.id,
    imageUrl = this.imageUrl,
    title = this.title,
    price = this.price,
    currency = Currency(
        symbol = "€",
        isRightPositioned = true
    ),
    isShared = this.isShared,
    category = this.category
)

fun List<Wish>.toPLO(): List<SimpleWishPLO> = this.map { it.toPLO() }