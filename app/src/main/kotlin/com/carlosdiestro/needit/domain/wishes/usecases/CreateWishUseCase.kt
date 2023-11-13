package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.core.design_system.components.lists.WishCategoryPlo
import com.carlosdiestro.needit.core.di.ApplicationScope
import com.carlosdiestro.needit.core.mappers.asDomain
import com.carlosdiestro.needit.domain.users.usecases.GetSignedInUserUseCase
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.repository.ImageRepository
import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateWishUseCase @Inject constructor(
    private val wishRepository: WishRepository,
    private val imageRepository: ImageRepository,
    private val getUserInfo: GetSignedInUserUseCase,
    @ApplicationScope private val scope: CoroutineScope
) {
    suspend operator fun invoke(
        imageLocalPath: String,
        title: String,
        subtitle: String,
        price: String,
        webUrl: String,
        description: String,
        category: WishCategoryPlo,
        size: String?,
        color: String?,
        isbn: String?
    ) {
        scope.launch {
            val userId = getUserInfo().first().id
            val wish = Wish.create(
                userId = userId,
                imageLocalPath = imageLocalPath,
                title = title,
                subtitle = subtitle,
                price = if (price.isEmpty()) 0.0 else price.toDouble(),
                webUrl = webUrl,
                description = description,
                category = category.asDomain(),
                size = size,
                color = color,
                isbn = isbn
            )
            wishRepository.create(wish)
            val cloudImageUrl = imageRepository.create(imageLocalPath, userId)
            wishRepository.update(
                wish.copy(imageUrl = cloudImageUrl)
            )
        }
    }
}