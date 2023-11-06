package com.carlosdiestro.needit.network.users

import com.carlosdiestro.needit.data.users.datasources.UserRemoteDatasource
import javax.inject.Inject

class UserRemoteDatasourceImpl @Inject constructor(
    private val usesCollection: UsersCollection
) : UserRemoteDatasource {

    override fun upsert(dto: UserDto) = usesCollection.upsert(dto)
    override fun updateUser(dto: UserDto) = usesCollection.update(dto)
}