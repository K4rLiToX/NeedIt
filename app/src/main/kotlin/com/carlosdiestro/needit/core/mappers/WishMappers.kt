package com.carlosdiestro.needit.core.mappers

import com.carlosdiestro.design_system.lists.HomeWishPlo
import com.carlosdiestro.design_system.lists.WishCategoryPlo
import com.carlosdiestro.needit.domain.wishes.Book
import com.carlosdiestro.needit.domain.wishes.Clothes
import com.carlosdiestro.needit.domain.wishes.Footwear
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.WishCategory
import com.carlosdiestro.needit.domain.wishes.WishInformation
import com.carlosdiestro.needit.domain.wishes.toWishCategory
import com.carlosdiestro.needit.framework.database.wishes.WishEntity
import com.carlosdiestro.needit.framework.network.wishes.WishDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID

fun WishEntity.asDomain(): Wish {
    val args = WishInformation(
        id = UUID.fromString(this.id),
        userId = this.userId,
        imageLocalPath = this.imageLocalPath,
        imageUrl = this.imageUrl,
        price = this.price,
        description = this.description,
        webUrl = this.webUrl,
        isShared = this.isShared,
        category = this.category.toWishCategory(),
        title = this.title,
        subtitle = this.subtitle
    )

    return when (args.category) {
        WishCategory.Clothes -> Wish.createClothes(args, this.size ?: "", this.color ?: "")
        WishCategory.Footwear -> Wish.createFootwear(args, this.size ?: "", this.color ?: "")
        WishCategory.Books -> Wish.createBook(args, this.isbn ?: "")
        WishCategory.Accessories, WishCategory.Grooming, WishCategory.Tech, WishCategory.Other ->
            Wish.createOther(args)
    }
}

fun List<WishEntity>.asDomain(): List<Wish> = this.map { it.asDomain() }

@JvmName("flowListWishEntityToDomain")
fun Flow<List<WishEntity>>.asDomain(): Flow<List<Wish>> = this.map { it.asDomain() }

@JvmName("flowWishEntityToDomain")
fun Flow<WishEntity>.asDomain(): Flow<Wish> = this.map { it.asDomain() }

fun Wish.asPlo(): HomeWishPlo = HomeWishPlo(
    id = this.id.toString(),
    imageUrl = this.imageLocalPath.ifEmpty { this.imageUrl },
    shared = this.isShared,
    category = this.category.asPlo()
)

fun WishCategory.asPlo(): WishCategoryPlo = WishCategoryPlo.entries[this.ordinal]
fun WishCategoryPlo.asDomain(): WishCategory = WishCategory.entries[this.ordinal]

fun List<Wish>.asPlo(): List<HomeWishPlo> = this.map { it.asPlo() }

fun Wish.asEntity(): WishEntity {
    val entity = WishEntity(
        id = this.id.toString(),
        userId = this.userId,
        imageLocalPath = this.imageLocalPath,
        imageUrl = this.imageUrl,
        title = this.title,
        subtitle = this.subtitle,
        price = this.price,
        category = this.category.toIntValue(),
        isShared = this.isShared,
        description = this.description,
        webUrl = this.webUrl
    )

    return when (this) {
        is Clothes -> entity.copy(size = this.size, color = this.color)
        is Footwear -> entity.copy(size = this.size, color = this.color)
        is Book -> entity.copy(isbn = this.isbn)
        else -> entity
    }
}

fun Wish.asDto(): WishDto {
    val dto = WishDto(
        id = id.toString(),
        userId = userId,
        imageUrl = imageUrl,
        price = price,
        description = description,
        webUrl = webUrl,
        isShared = true,
        category = category.toIntValue(),
        title = title,
        subtitle = subtitle,
    )

    return when (this) {
        is Clothes -> dto.copy(size = this.size, color = this.color)
        is Footwear -> dto.copy(size = this.size, color = this.color)
        is Book -> dto.copy(isbn = this.isbn)
        else -> dto
    }
}