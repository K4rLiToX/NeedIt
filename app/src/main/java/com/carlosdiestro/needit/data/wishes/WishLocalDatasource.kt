package com.carlosdiestro.needit.data.wishes

import com.carlosdiestro.needit.domain.wishes.Wish
import kotlinx.coroutines.flow.Flow

interface WishLocalDatasource {
    val wishes: Flow<List<Wish>>
    suspend fun getWish(id: Long): Wish
}