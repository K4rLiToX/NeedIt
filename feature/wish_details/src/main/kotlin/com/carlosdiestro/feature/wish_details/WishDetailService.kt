package com.carlosdiestro.feature.wish_details

import com.carlosdiestro.user.domain.UserRepository
import com.carlosdiestro.wish.domain.model.Book
import com.carlosdiestro.wish.domain.model.Clothes
import com.carlosdiestro.wish.domain.model.Footwear
import com.carlosdiestro.wish.domain.model.Other
import com.carlosdiestro.wish.domain.model.Wish
import com.carlosdiestro.wish.domain.repository.WishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WishDetailService @Inject constructor(
    private val wishRepository: WishRepository,
    userRepository: UserRepository
) {

    val isUserAnonymous = userRepository.currentUser.map { it.isAnonymous }

    fun getWish(id: String): Flow<Wish> = wishRepository.getCurrentUserWish(id)

    suspend fun shareWish(wish: Wish) = wishRepository.update(
        when (wish) {
            is Clothes -> wish.copy(isShared = true)
            is Footwear -> wish.copy(isShared = true)
            is Book -> wish.copy(isShared = true)
            is Other -> wish.copy(isShared = true)
        }
    )

    suspend fun lockWish(wish: Wish) = wishRepository.update(
        when (wish) {
            is Clothes -> wish.copy(isShared = false)
            is Footwear -> wish.copy(isShared = false)
            is Book -> wish.copy(isShared = false)
            is Other -> wish.copy(isShared = false)
        }
    )
}