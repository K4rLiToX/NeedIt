package com.carlosdiestro.remote_database.firestore.wishes

import com.carlosdiestro.wish.data.datasource.WishRemoteDatasource
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

internal class WishRemoteDatasourceImpl @Inject constructor(
    private val wishesCollection: WishesCollection
) : WishRemoteDatasource {

    override fun upsert(wish: Wish) = wishesCollection.upsert(wish.asDto())

    override fun delete(wish: Wish) =
        wishesCollection.delete(wish.asDto())

    override fun getUsersWishes(ids: List<String>): Flow<List<Wish>> =
        wishesCollection.getUsersWishes(ids).asDomain()

    override fun getUserWishes(userId: String): Flow<List<Wish>> =
        wishesCollection.getUserWishes(userId).asDomain()

    override fun getUserWish(wishId: String): Flow<Wish?> =
        wishesCollection.getUserWish(wishId).asDomain()
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

fun WishDto.asDomain(): Wish {
    val args = WishInformation(
        id = UUID.fromString(this.id),
        userId = this.userId,
        imageLocalPath = "",
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

fun List<WishDto>.asDomain(): List<Wish> = this.map { it.asDomain() }

@JvmName("flowListWishDtoAsDomain")
fun Flow<List<WishDto>>.asDomain(): Flow<List<Wish>> = this.map { it.asDomain() }

@JvmName("flowWishDtoAsDomain")
fun Flow<WishDto?>.asDomain(): Flow<Wish?> = this.map { it?.asDomain() }