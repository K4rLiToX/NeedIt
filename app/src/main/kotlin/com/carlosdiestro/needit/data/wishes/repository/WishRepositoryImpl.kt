package com.carlosdiestro.needit.data.wishes.repository

import com.carlosdiestro.needit.data.wishes.datasources.WishLocalDatasource
import com.carlosdiestro.needit.data.wishes.datasources.WishRemoteDatasource
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class WishRepositoryImpl @Inject constructor(
    private val localDatasource: WishLocalDatasource,
    private val remoteDatasource: WishRemoteDatasource,
) : WishRepository {

    override val wishes: Flow<List<Wish>>
        get() = localDatasource.wishes

    override val sharedWishes: Flow<List<Wish>>
        get() = localDatasource.sharedWishes

    override fun getWish(id: Long): Flow<Wish> =
        localDatasource.getWish(id)

    override suspend fun insertWish(wish: Wish): Long = localDatasource.insertWish(wish)

    override suspend fun updateWish(wish: Wish) = kotlin.run {
        localDatasource.updateWish(wish)
        if (wish.isShared) remoteDatasource.update(wish)
    }

    override suspend fun removeWish(id: Long, cloudId: String) = kotlin.run {
        localDatasource.removeWish(id)
        if (cloudId.isNotEmpty()) {
            val userId = getWish(id).first().userId
            remoteDatasource.delete(cloudId, userId)
        }
    }

    override suspend fun shareWish(id: Long) = kotlin.run {
        val wish = getWish(id).first()
        val cloudId = remoteDatasource.insert(wish)
        localDatasource.shareWish(id, cloudId)
    }

    override suspend fun lockWish(id: Long) = kotlin.run {
        val wish = getWish(id).first()
        localDatasource.lockWish(id)
        remoteDatasource.delete(wish.cloudId, wish.userId)
    }
}