package com.carlosdiestro.feature.upsert_wish

import com.carlosdiestro.user.domain.UserRepository
import com.carlosdiestro.wish.domain.model.Book
import com.carlosdiestro.wish.domain.model.Clothes
import com.carlosdiestro.wish.domain.model.Footwear
import com.carlosdiestro.wish.domain.model.Other
import com.carlosdiestro.wish.domain.model.Wish
import com.carlosdiestro.wish.domain.model.WishCategory
import com.carlosdiestro.wish.domain.model.WishInformation
import com.carlosdiestro.wish.domain.repository.ImageRepository
import com.carlosdiestro.wish.domain.repository.WishRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UpsertWishService @Inject constructor(
    private val userRepository: UserRepository,
    private val wishRepository: WishRepository,
    private val imageRepository: ImageRepository,
    private val imageCompressor: ImageCompressor
) {

    suspend fun getWish(id: String): Wish = wishRepository.getCurrentUserWish(id).first()

    suspend fun getImageLocalPath(): String = imageRepository.getLocalPath()

    suspend fun update(
        wish: Wish,
        price: Double,
        description: String,
        webUrl: String,
        title: String,
        subtitle: String,
        size: String?,
        color: String?,
        isbn: String?
    ) = wishRepository.update(
        when (wish) {
            is Clothes -> wish.copy(
                price = price,
                description = description,
                webUrl = webUrl,
                title = title,
                subtitle = subtitle,
                size = size ?: "",
                color = color ?: ""
            )

            is Footwear -> wish.copy(
                price = price,
                description = description,
                webUrl = webUrl,
                title = title,
                subtitle = subtitle,
                size = size ?: "",
                color = color ?: ""
            )

            is Book -> wish.copy(
                price = price,
                description = description,
                webUrl = webUrl,
                title = title,
                subtitle = subtitle,
                isbn = isbn ?: ""
            )

            is Other -> wish.copy(
                price = price,
                description = description,
                webUrl = webUrl,
                title = title,
                subtitle = subtitle
            )
        }
    )

    suspend fun create(
        imageLocalPath: String,
        price: Double,
        description: String,
        webUrl: String,
        category: WishCategory,
        title: String,
        subtitle: String,
        size: String?,
        color: String?,
        isbn: String?
    ) = kotlin.run {
        val args = createWishInformation(
            imageLocalPath = imageLocalPath,
            price = price,
            description = description,
            webUrl = webUrl,
            category = category,
            title = title,
            subtitle = subtitle
        )
        val compressedImage = imageCompressor.compress(imageLocalPath)
        val cloudImageUrl = imageRepository.create(compressedImage, args.userId)
        val info = args.copy(imageUrl = cloudImageUrl)
        val wish = when (args.category) {
            WishCategory.Clothes -> Wish.createClothes(info, size ?: "", color ?: "")
            WishCategory.Footwear -> Wish.createFootwear(info, size ?: "", color ?: "")
            WishCategory.Books -> Wish.createBook(info, isbn ?: "")
            WishCategory.Accessories, WishCategory.Grooming, WishCategory.Tech, WishCategory
                .Other -> Wish.createOther(info)
        }
        wishRepository.create(wish)
    }

    private suspend fun createWishInformation(
        imageLocalPath: String,
        price: Double,
        description: String,
        webUrl: String,
        category: WishCategory,
        title: String,
        subtitle: String
    ): WishInformation = kotlin.run {
        val userId = userRepository.getCurrentUserId()
        return WishInformation(
            userId = userId,
            imageLocalPath = imageLocalPath,
            price = price,
            description = description,
            webUrl = webUrl,
            category = category,
            title = title,
            subtitle = subtitle
        )
    }
}