package com.carlosdiestro.local_database.wishes

import com.carlosdiestro.wish.data.datasource.WishLocalDatasource
import com.carlosdiestro.wish.domain.model.Book
import com.carlosdiestro.wish.domain.model.Clothes
import com.carlosdiestro.wish.domain.model.Footwear
import com.carlosdiestro.wish.domain.model.Wish
import com.carlosdiestro.wish.domain.model.WishCategory
import com.carlosdiestro.wish.domain.model.WishInformation
import com.carlosdiestro.wish.domain.model.toWishCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

internal class WishLocalDatasourceImpl @Inject constructor(
    private val dao: WishDao
) : WishLocalDatasource {

    override val wishes: Flow<List<Wish>> = dao.getAll().asDomain()
    override val sharedWishes: Flow<List<Wish>> = dao.getShared().asDomain()
    override fun getWish(id: String): Flow<Wish> = dao.getWish(id).asDomain()
    override suspend fun create(wish: Wish) = dao.create(wish.asEntity())
    override suspend fun update(wish: Wish) = dao.update(wish.asEntity())
    override suspend fun delete(wish: Wish) = dao.delete(wish.asEntity())
}

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

fun List<WishEntity>.asDomain(): List<Wish> =
    this.map { it.asDomain() }

@JvmName("flowListWishEntityToDomain")
fun Flow<List<WishEntity>>.asDomain(): Flow<List<Wish>> =
    this.map { it.asDomain() }

@JvmName("flowWishEntityToDomain")
fun Flow<WishEntity>.asDomain(): Flow<Wish> =
    this.map { it.asDomain() }

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

