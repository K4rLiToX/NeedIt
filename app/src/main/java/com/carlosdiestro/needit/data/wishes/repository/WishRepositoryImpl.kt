package com.carlosdiestro.needit.data.wishes.repository

import com.carlosdiestro.needit.core.di.ApplicationScope
import com.carlosdiestro.needit.core.di.IoDispatcher
import com.carlosdiestro.needit.data.wishes.datasources.WishLocalDatasource
import com.carlosdiestro.needit.data.wishes.datasources.WishRemoteDatasource
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WishRepositoryImpl @Inject constructor(
    private val localDatasource: WishLocalDatasource,
    private val remoteDatasource: WishRemoteDatasource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @ApplicationScope private val appDispatcher: CoroutineScope
) : WishRepository {
    override val wishes: Flow<List<Wish>>
        get() = localDatasource.wishes.flowOn(ioDispatcher)

    override val sharedWishes: Flow<List<Wish>>
        get() = localDatasource.sharedWishes.flowOn(ioDispatcher)

    override fun getWish(id: Long): Flow<Wish> =
        localDatasource.getWish(id).flowOn(ioDispatcher)

    override suspend fun insertWish(wish: Wish): Long =
        withContext(appDispatcher.coroutineContext) {
            localDatasource.insertWish(wish)
        }

    override suspend fun updateWish(wish: Wish) = withContext(appDispatcher.coroutineContext) {
        localDatasource.updateWish(wish)
        if (wish.isShared) remoteDatasource.update(wish)
    }

    override suspend fun removeWish(id: Long, cloudId: String) = withContext(ioDispatcher) {
        localDatasource.removeWish(id)
        if (cloudId.isNotEmpty()) remoteDatasource.delete(cloudId)
    }

    override suspend fun shareWish(id: Long) = withContext(ioDispatcher) {
        val wish = getWish(id).first()
        val cloudId = remoteDatasource.insert(wish)
        localDatasource.shareWish(id, cloudId)
    }

    override suspend fun lockWish(id: Long) = withContext(ioDispatcher) {
        val wish = getWish(id).first()
        localDatasource.lockWish(id)
        remoteDatasource.delete(wish.cloudId)
    }
}