package com.carlosdiestro.needit.network.datasources

import com.carlosdiestro.needit.core.mappers.toDto
import com.carlosdiestro.needit.data.wishes.datasources.WishRemoteDatasource
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.network.collections.WishesCollection
import javax.inject.Inject

class WishRemoteDatasourceImpl @Inject constructor(
    private val wishesCollection: WishesCollection
) : WishRemoteDatasource {

    override suspend fun insert(wish: Wish): String = wishesCollection.insert(wish.toDto())
    override suspend fun delete(cloudId: String) = wishesCollection.delete(cloudId)
}