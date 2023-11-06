package com.carlosdiestro.needit.data.wishes.datasources

import com.carlosdiestro.needit.domain.wishes.Wish

interface WishRemoteDatasource {
    suspend fun insert(wish: Wish): String
    fun delete(cloudId: String, userId: String)
    fun update(wish: Wish)
}