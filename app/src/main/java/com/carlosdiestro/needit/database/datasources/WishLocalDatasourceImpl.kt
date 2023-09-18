package com.carlosdiestro.needit.database.datasources

import com.carlosdiestro.needit.core.mappers.toDomain
import com.carlosdiestro.needit.core.mappers.toEntity
import com.carlosdiestro.needit.data.wishes.datasources.WishLocalDatasource
import com.carlosdiestro.needit.database.dao.WishDao
import com.carlosdiestro.needit.domain.wishes.Wish
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishLocalDatasourceImpl @Inject constructor(
    private val dao: WishDao
) : WishLocalDatasource {
    override val wishes: Flow<List<Wish>>
        get() = dao.getAll().toDomain()
    override val sharedWishes: Flow<List<Wish>>
        get() = dao.getShared().toDomain()

    override fun getWish(id: Long): Flow<Wish> = dao.getWish(id).toDomain()
    override suspend fun insertWish(wish: Wish): Long = dao.insert(wish.toEntity())
    override suspend fun updateWish(wish: Wish) = dao.update(wish.toEntity())
    override suspend fun removeWish(id: Long) = dao.remove(id)
    override suspend fun shareWish(id: Long, cloudId: String) = dao.share(id, cloudId)
    override suspend fun lockWish(id: Long) = dao.lock(id)
}