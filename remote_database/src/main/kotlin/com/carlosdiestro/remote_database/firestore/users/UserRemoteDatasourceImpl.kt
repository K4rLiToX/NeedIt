package com.carlosdiestro.remote_database.firestore.users

import com.carlosdiestro.user.data.datasource.UserRemoteDatasource
import com.carlosdiestro.user.domain.User
import javax.inject.Inject

internal class UserRemoteDatasourceImpl @Inject constructor(
    private val usersCollection: UsersCollection
) : UserRemoteDatasource {

    override fun upsert(user: User) = usersCollection.upsert(user.asDto())
}

fun User.asDto(): UserDto =
    UserDto(
        id = id,
        username = username,
        email = email,
        profilePictureUrl = profilePictureUrl,
        isAnonymous = isAnonymous
    )