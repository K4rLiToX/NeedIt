package com.carlosdiestro.needit.database.wishes

import com.carlosdiestro.needit.core.mappers.asDomain
import com.carlosdiestro.needit.core.mappers.asEntity
import com.carlosdiestro.needit.data.wishes.datasources.WishLocalDatasource
import com.carlosdiestro.needit.domain.wishes.Wish
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class WishLocalDatasourceImpl @Inject constructor(
    private val dao: WishDao
) : WishLocalDatasource {

    override val wishes: Flow<List<Wish>> = dao.getAll().asDomain()
    override val sharedWishes: Flow<List<Wish>> = dao.getShared().asDomain()
    override fun getWish(id: Long): Flow<Wish> = dao.getWish(id).asDomain()
    override suspend fun insertWish(wish: Wish): Long = dao.insert(wish.asEntity())
    override suspend fun updateWish(wish: Wish) = dao.update(wish.asEntity())
    override suspend fun removeWish(id: Long) = dao.remove(id)
    override suspend fun shareWish(id: Long, cloudId: String) = dao.share(id, cloudId)
    override suspend fun lockWish(id: Long) = dao.lock(id)
}