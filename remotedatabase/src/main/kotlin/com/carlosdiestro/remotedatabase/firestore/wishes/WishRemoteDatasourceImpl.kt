package com.carlosdiestro.remotedatabase.firestore.wishes

import javax.inject.Inject

internal class WishRemoteDatasourceImpl @Inject constructor(
    private val wishesCollection: WishesCollection
) : WishRemoteDatasource {

    override fun upsert(wish: WishDto) = wishesCollection.upsert(wish)

    override fun delete(cloudId: String, userId: String) =
        wishesCollection.delete(cloudId, userId)
}