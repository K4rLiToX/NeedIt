package com.carlosdiestro.needit.core.mappers

import com.carlosdiestro.needit.core.design_system.components.lists.HomeWishPlo
import com.carlosdiestro.needit.core.design_system.components.lists.WishCategoryPlo
import com.carlosdiestro.needit.database.wishes.WishEntity
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.WishCategory
import com.carlosdiestro.needit.domain.wishes.toWishCategory
import com.carlosdiestro.needit.network.wishes.WishDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID

fun WishEntity.asDomain(): Wish = Wish.create(
    id = UUID.fromString(this.id),
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

fun Wish.asEntity(): WishEntity = WishEntity(
    id = this.id.toString(),
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

fun Wish.asDto(): WishDto = WishDto(
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
    size = this.size,
    color = this.color,
    isbn = this.isbn
)