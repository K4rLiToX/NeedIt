package com.carlosdiestro.needit.core.mappers

import com.carlosdiestro.needit.core.design_system.components.cards.SimpleWishPLO
import com.carlosdiestro.needit.core.design_system.components.navigation.toIntValue
import com.carlosdiestro.needit.core.design_system.components.navigation.toWishCategory
import com.carlosdiestro.needit.database.entities.WishEntity
import com.carlosdiestro.needit.domain.wishes.Wish
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
fun Flow<List<WishEntity>>.toDomain(): Flow<List<Wish>> = this.map { it.toDomain() }

fun Wish.toPLO(): SimpleWishPLO = SimpleWishPLO(
    id = this.id,
    userId = userId,
    imageUrl = this.imageUrl.ifEmpty { this.imageLocalPath },
    title = this.title,
    isShared = this.isShared,
    category = this.category
)

fun List<Wish>.toPLO(): List<SimpleWishPLO> = this.map { it.toPLO() }

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

