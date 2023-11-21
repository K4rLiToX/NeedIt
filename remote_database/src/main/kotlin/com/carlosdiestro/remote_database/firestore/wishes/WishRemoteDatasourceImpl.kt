package com.carlosdiestro.remote_database.firestore.wishes

import com.carlosdiestro.wish.data.datasource.WishRemoteDatasource
import com.carlosdiestro.wish.domain.model.Book
import com.carlosdiestro.wish.domain.model.Clothes
import com.carlosdiestro.wish.domain.model.Footwear
import com.carlosdiestro.wish.domain.model.Wish
import javax.inject.Inject

internal class WishRemoteDatasourceImpl @Inject constructor(
    private val wishesCollection: WishesCollection
) : WishRemoteDatasource {

    override fun upsert(wish: Wish) = wishesCollection.upsert(wish.asDto())

    override fun delete(cloudId: String, userId: String) =
        wishesCollection.delete(cloudId, userId)
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