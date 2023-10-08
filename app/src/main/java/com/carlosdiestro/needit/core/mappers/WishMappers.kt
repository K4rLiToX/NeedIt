package com.carlosdiestro.needit.core.mappers

import com.carlosdiestro.needit.core.design_system.components.lists.HomeWishPlo
import com.carlosdiestro.needit.core.design_system.components.lists.WishCategoryPlo
import com.carlosdiestro.needit.core.design_system.components.navigation.toIntValue
import com.carlosdiestro.needit.core.design_system.components.navigation.toWishCategory
import com.carlosdiestro.needit.database.entities.WishEntity
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.WishCategory
import com.carlosdiestro.needit.network.dtos.WishDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun WishEntity.toDomain(): Wish = Wish(
    id = this.id ?: -1,
    cloudId = this.cloudId,
    userId = this.userId,
    imageLocalPath = this.imageLocalPath,
    imageUrl = this.imageUrl,
    price = this.price,
    description = this.description,
    webUrl = this.webUrl,
    category = this.category.toWishCategory(),
    isShared = this.isShared,
    title = this.title,
    subtitle = this.subtitle,
    size = this.size,
    color = this.color,
    isbn = this.isbn
)

fun List<WishEntity>.toDomain(): List<Wish> = this.map { it.toDomain() }

@JvmName("flowListWishEntityToDomain")
fun Flow<List<WishEntity>>.toDomain(): Flow<List<Wish>> = this.map { it.toDomain() }

@JvmName("flowWishEntityToDomain")
fun Flow<WishEntity>.toDomain(): Flow<Wish> = this.map { it.toDomain() }

fun Wish.asPlo(): HomeWishPlo = HomeWishPlo(
    id = this.id,
    imageUrl = this.imageUrl.ifEmpty { this.imageLocalPath },
    shared = this.isShared,
    category = this.category.asPlo()
)

fun WishCategory.asPlo(): WishCategoryPlo = when (this) {
    WishCategory.Clothes -> WishCategoryPlo.Clothes
    WishCategory.Footwear ->  WishCategoryPlo.Footwear
    WishCategory.Accessories ->  WishCategoryPlo.Accessories
    WishCategory.Grooming ->  WishCategoryPlo.Grooming
    WishCategory.Books ->  WishCategoryPlo.Books
    WishCategory.Tech -> WishCategoryPlo.Tech
    WishCategory.Other ->  WishCategoryPlo.Other
}

fun List<Wish>.asPlo(): List<HomeWishPlo> = this.map { it.asPlo() }

fun Wish.toEntity(): WishEntity = WishEntity(
    id = if (this.id == -1L) null else this.id,
    cloudId = this.cloudId,
    userId = this.userId,
    imageLocalPath = this.imageLocalPath,
    imageUrl = this.imageUrl,
    title = this.title,
    subtitle = this.subtitle,
    price = this.price,
    category = this.category.toIntValue(),
    isShared = this.isShared,
    description = this.description,
    webUrl = this.webUrl,
    size = this.size,
    color = this.color,
    isbn = this.isbn
)

fun Wish.toDto(): WishDto = WishDto(
    id = id,
    userId = userId,
    imageUrl = imageUrl,
    price = price,
    description = description,
    webUrl = webUrl,
    isShared = true,
    category = category.toIntValue(),
    title = title,
    subtitle = subtitle,
    size = this.size,
    color = this.color,
    isbn = this.isbn
)

