package com.carlosdiestro.needit.data.wishes.datasources

import com.carlosdiestro.needit.network.wishes.WishDto

interface WishRemoteDatasource {
    suspend fun create(wish: WishDto): String
    fun delete(cloudId: String, userId: String)
    fun update(cloudId: String, wish: WishDto)
}