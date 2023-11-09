package com.carlosdiestro.needit.database.wishes

import com.carlosdiestro.needit.data.wishes.datasources.WishLocalDatasource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class WishLocalDatasourceImpl @Inject constructor(
    private val dao: WishDao
) : WishLocalDatasource {

    override val wishes: Flow<List<WishEntity>> = dao.getAll()

    override val sharedWishes: Flow<List<WishEntity>> = dao.getShared()
    override fun getWish(id: Long): Flow<WishEntity> = dao.getWish(id)
    override suspend fun create(wish: WishEntity): Long = dao.create(wish)
    override suspend fun update(wish: WishEntity) = dao.update(wish)
    override suspend fun delete(wish: WishEntity) = dao.delete(wish)
}