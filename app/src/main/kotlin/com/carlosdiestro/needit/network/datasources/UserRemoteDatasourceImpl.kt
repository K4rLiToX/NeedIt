package com.carlosdiestro.needit.network.datasources

import com.carlosdiestro.needit.data.users.datasources.UserRemoteDatasource
import com.carlosdiestro.needit.network.collections.UsersCollection
import com.carlosdiestro.needit.network.dtos.UserDto
import javax.inject.Inject

class UserRemoteDatasourceImpl @Inject constructor(
    private val usesCollection: UsersCollection
) : UserRemoteDatasource {

    override suspend fun upsert(dto: UserDto) = usesCollection.upsert(dto)
    override suspend fun updateUser(dto: UserDto) = usesCollection.update(dto)
}