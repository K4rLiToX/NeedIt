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

    override suspend fun create(wish: Wish) = localDatasource.create(wish)

    override suspend fun update(wish: Wish) {
        localDatasource.update(wish)
        if (wish.isShared) remoteDatasource.upsert(wish)
        else remoteDatasource.delete(wish)
    }

    override suspend fun delete(wish: Wish) = kotlin.run {
        localDatasource.delete(wish)
        if (wish.isShared) remoteDatasource.delete(wish)
    }

    override fun getCurrentUserWishes(): Flow<List<Wish>> = localDatasource.wishes

    override fun getCurrentUserWish(id: String): Flow<Wish> = localDatasource.getWish(id)

    override fun getFriendsWishes(ids: List<String>): Flow<List<Wish>> =
        remoteDatasource.getUsersWishes(ids)

    override fun getFriendWishes(userId: String): Flow<List<Wish>> =
        remoteDatasource.getUserWishes(userId)

    override fun getFriendWish(id: String): Flow<Wish?> =
        remoteDatasource.getUserWish(id)
}