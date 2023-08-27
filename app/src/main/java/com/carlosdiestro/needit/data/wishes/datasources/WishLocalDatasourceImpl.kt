package com.carlosdiestro.needit.data.wishes.datasources

import com.carlosdiestro.needit.core.mappers.toDomain
import com.carlosdiestro.needit.core.mappers.toEntity
import com.carlosdiestro.needit.database.dao.WishDao
import com.carlosdiestro.needit.domain.wishes.Wish
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishLocalDatasourceImpl @Inject constructor(
    private val dao: WishDao
) : WishLocalDatasource {
    override val wishes: Flow<List<Wish>>
        get() = dao.getAll().toDomain()

    override suspend fun getWish(id: Long): Wish = dao.getWish(id).toDomain()
    override suspend fun upsertWish(wish: Wish) = dao.upsert(wish.toEntity())
    override suspend fun removeWish(id: Long) = dao.remove(id)
    override suspend fun shareWish(id: Long) = dao.share(id)
    override suspend fun lockWish(id: Long) = dao.lock(id)
}