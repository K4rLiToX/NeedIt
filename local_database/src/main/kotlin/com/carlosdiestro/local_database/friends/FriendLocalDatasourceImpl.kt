package com.carlosdiestro.local_database.friends

import com.carlosdiestro.friend.data.FriendLocalDatasource
import com.carlosdiestro.friend.domain.Friend
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FriendLocalDatasourceImpl @Inject constructor(
    private val dao: FriendDao
) : FriendLocalDatasource {

    override suspend fun upsert(friend: Friend) = dao.upsert(friend.asEntity())

    override suspend fun delete(friend: Friend) = dao.delete(friend.asEntity())

    override fun getAll(): Flow<List<Friend>> = dao.getAll().asDomain()
}

fun Friend.asEntity(): FriendEntity = FriendEntity(
    id = id,
    username = username,
    email = email,
    profilePictureUrl = profilePictureUrl
)

fun FriendEntity.asDomain(): Friend = Friend(
    id = id,
    username = username,
    email = email,
    profilePictureUrl = profilePictureUrl
)

fun List<FriendEntity>.asDomain(): List<Friend> = this.map { it.asDomain() }

fun Flow<List<FriendEntity>>.asDomain(): Flow<List<Friend>> = this.map { it.asDomain() }