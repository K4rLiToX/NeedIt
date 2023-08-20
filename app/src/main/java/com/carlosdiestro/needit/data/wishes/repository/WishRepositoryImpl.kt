package com.carlosdiestro.needit.data.wishes.repository

import com.carlosdiestro.needit.core.di.ApplicationScope
import com.carlosdiestro.needit.core.di.IoDispatcher
import com.carlosdiestro.needit.data.wishes.datasources.WishLocalDatasource
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WishRepositoryImpl @Inject constructor(
    private val localDatasource: WishLocalDatasource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @ApplicationScope private val appDispatcher: CoroutineScope
) : WishRepository {
    override val wishes: Flow<List<Wish>>
        get() = localDatasource.wishes.flowOn(ioDispatcher)

    override suspend fun getWish(id: Long): Wish = withContext(ioDispatcher) {
        localDatasource.getWish(id)
    }

    override suspend fun upsertWish(wish: Wish) {
        appDispatcher.launch {
            localDatasource.upsertWish(wish)
        }
    }

    override suspend fun removeWish(id: Long) = withContext(ioDispatcher) {
        localDatasource.removeWish(id)
    }

    override suspend fun shareWish(id: Long) = withContext(ioDispatcher) {
        localDatasource.shareWish(id)
    }

    override suspend fun lockWish(id: Long) = withContext(ioDispatcher) {
        localDatasource.lockWish(id)
    }
}