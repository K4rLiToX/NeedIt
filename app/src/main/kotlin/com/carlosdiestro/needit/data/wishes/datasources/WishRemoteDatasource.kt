package com.carlosdiestro.needit.data.wishes.datasources

import com.carlosdiestro.needit.framework.network.wishes.WishDto

interface WishRemoteDatasource {
    fun upsert(wish: WishDto)
    fun delete(cloudId: String, userId: String)
}