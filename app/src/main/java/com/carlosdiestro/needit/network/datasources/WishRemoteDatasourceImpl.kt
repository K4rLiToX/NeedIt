package com.carlosdiestro.needit.network.datasources

import com.carlosdiestro.needit.core.mappers.asDto
import com.carlosdiestro.needit.data.wishes.datasources.WishRemoteDatasource
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.network.collections.WishesCollection
import javax.inject.Inject

class WishRemoteDatasourceImpl @Inject constructor(
    private val wishesCollection: WishesCollection
) : WishRemoteDatasource {

    override suspend fun insert(wish: Wish): String = wishesCollection.insert(wish.asDto())
    override suspend fun delete(cloudId: String) = wishesCollection.delete(cloudId)
    override suspend fun update(wish: Wish) = wishesCollection.update(wish.cloudId, wish.asDto())
}