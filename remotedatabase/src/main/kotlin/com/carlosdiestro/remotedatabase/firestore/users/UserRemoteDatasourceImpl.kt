package com.carlosdiestro.remotedatabase.firestore.users

import javax.inject.Inject

internal class UserRemoteDatasourceImpl @Inject constructor(
    private val usersCollection: UsersCollection
) : UserRemoteDatasource {

    override fun upsert(user: UserDto) = usersCollection.upsert(user)
}