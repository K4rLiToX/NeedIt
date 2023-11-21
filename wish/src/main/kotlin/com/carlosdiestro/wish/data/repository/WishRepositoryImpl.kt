package com.carlosdiestro.wish.data.repository

import com.carlosdiestro.wish.data.datasource.WishLocalDatasource
import com.carlosdiestro.wish.data.datasource.WishRemoteDatasource
import com.carlosdiestro.wish.domain.model.Wish
import com.carlosdiestro.wish.domain.repository.WishRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishRepositoryImpl @Inject constructor(
    private val localDatasource: WishLocalDatasource,
    private val remoteDatasource: WishRemoteDatasource,
) : WishRepository {

    override val wishes: Flow<List<Wish>>
        get() = localDatasource.wishes

    override val sharedWishes: Flow<List<Wish>>
        get() = localDatasource.sharedWishes

    override fun getWish(id: String): Flow<Wish> =
        localDatasource.getWish(id)

    override suspend fun create(wish: Wish) = localDatasource.create(wish)

    override suspend fun update(wish: Wish) {
        localDatasource.update(wish)
        if (wish.isShared) remoteDatasource.upsert(wish)
        else remoteDatasource.delete(wish.id.toString(), wish.userId)
    }

    override suspend fun delete(wish: Wish) = kotlin.run {
        localDatasource.delete(wish)
        if (wish.isShared) remoteDatasource.delete(wish.id.toString(), wish.userId)
    }
}