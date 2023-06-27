package com.carlosdiestro.needit.domain.wishes

import kotlinx.coroutines.flow.Flow

interface WishRepository {
    val wishes: Flow<List<Wish>>
}