package com.carlosdiestro.needit.network.users

import com.carlosdiestro.needit.data.users.UserRemoteDatasource
import javax.inject.Inject

internal class UserRemoteDatasourceImpl @Inject constructor(
    private val usersCollection: UsersCollection
) : UserRemoteDatasource {

    override fun upsert(user: UserDto) = usersCollection.upsert(user)
}