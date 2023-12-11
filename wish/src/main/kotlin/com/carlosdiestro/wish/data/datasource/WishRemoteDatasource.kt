package com.carlosdiestro.wish.data.datasource

import com.carlosdiestro.wish.domain.model.Wish
import kotlinx.coroutines.flow.Flow

interface WishRemoteDatasource {
    fun upsert(wish: Wish)
    fun delete(wish: Wish)
    fun getUserWishes(userId: String): Flow<List<Wish>>
    fun getUserWish(wishId: String): Flow<Wish?>
}