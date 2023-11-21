package com.carlosdiestro.remotedatabase.firestore.wishes

interface WishRemoteDatasource {
    fun upsert(wish: WishDto)
    fun delete(cloudId: String, userId: String)
}