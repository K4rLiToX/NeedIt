package com.carlosdiestro.needit.network.wishes

import com.carlosdiestro.needit.data.wishes.datasources.WishRemoteDatasource
import javax.inject.Inject

internal class WishRemoteDatasourceImpl @Inject constructor(
    private val wishesCollection: WishesCollection
) : WishRemoteDatasource {

    override suspend fun create(wish: WishDto): String = wishesCollection.create(wish)

    override fun delete(cloudId: String, userId: String) =
        wishesCollection.delete(cloudId, userId)

    override fun update(cloudId: String, wish: WishDto) = wishesCollection.update(cloudId, wish)
}