package com.carlosdiestro.needit.network.wishes

import com.carlosdiestro.needit.core.mappers.asDto
import com.carlosdiestro.needit.data.wishes.datasources.WishRemoteDatasource
import com.carlosdiestro.needit.domain.wishes.Wish
import javax.inject.Inject

class WishRemoteDatasourceImpl @Inject constructor(
    private val wishesCollection: WishesCollection
) : WishRemoteDatasource {

    override suspend fun insert(wish: Wish): String = wishesCollection.insert(wish.asDto())

    override fun delete(cloudId: String, userId: String) =
        wishesCollection.delete(cloudId, userId)

    override fun update(wish: Wish) = wishesCollection.update(wish.cloudId, wish.asDto())
}