package com.carlosdiestro.needit.data.users

import com.carlosdiestro.needit.network.UsersCollection
import com.carlosdiestro.needit.network.dtos.UserDto
import javax.inject.Inject

class UserRemoteDatasourceImpl @Inject constructor(
    private val usesCollection: UsersCollection
) : UserRemoteDatasource {
    override suspend fun insertUser(dto: UserDto) = usesCollection.insert(dto)
}