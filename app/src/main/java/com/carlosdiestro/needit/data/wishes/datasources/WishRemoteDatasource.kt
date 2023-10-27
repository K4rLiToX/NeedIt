package com.carlosdiestro.needit.data.wishes.datasources

import com.carlosdiestro.needit.domain.wishes.Wish

interface WishRemoteDatasource {
    suspend fun insert(wish: Wish): String
    suspend fun delete(cloudId: String, userId: String)
    suspend fun update(wish: Wish)
}