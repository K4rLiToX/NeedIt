package com.carlosdiestro.wish.data.datasource

import com.carlosdiestro.wish.domain.model.Wish

interface WishRemoteDatasource {
    fun upsert(wish: Wish)
    fun delete(cloudId: String, userId: String)
}