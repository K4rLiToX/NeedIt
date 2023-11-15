package com.carlosdiestro.needit.data.wishes.repository

import com.carlosdiestro.needit.core.mappers.asDomain
import com.carlosdiestro.needit.core.mappers.asDto
import com.carlosdiestro.needit.core.mappers.asEntity
import com.carlosdiestro.needit.data.wishes.datasources.WishLocalDatasource
import com.carlosdiestro.needit.data.wishes.datasources.WishRemoteDatasource
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class WishRepositoryImpl @Inject constructor(
    private val localDatasource: WishLocalDatasource,
    private val remoteDatasource: WishRemoteDatasource,
) : WishRepository {

    override val wishes: Flow<List<Wish>>
        get() = localDatasource.wishes.asDomain()

    override val sharedWishes: Flow<List<Wish>>
        get() = localDatasource.sharedWishes.asDomain()

    override fun getWish(id: String): Flow<Wish> =
        localDatasource.getWish(id).asDomain()

    override suspend fun create(wish: Wish) = localDatasource.create(wish.asEntity())

    override suspend fun update(wish: Wish) {
        localDatasource.update(wish.asEntity())
        if (wish.isShared) remoteDatasource.upsert(wish.asDto())
        else remoteDatasource.delete(wish.id.toString(), wish.userId)
    }

    override suspend fun delete(wish: Wish) = kotlin.run {
        localDatasource.delete(wish.asEntity())
        if (wish.isShared) remoteDatasource.delete(wish.id.toString(), wish.userId)
    }
}