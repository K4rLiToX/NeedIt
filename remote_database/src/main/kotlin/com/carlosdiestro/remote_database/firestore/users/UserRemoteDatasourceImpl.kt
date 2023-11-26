package com.carlosdiestro.remote_database.firestore.users

import com.carlosdiestro.user.data.datasource.UserRemoteDatasource
import com.carlosdiestro.user.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class UserRemoteDatasourceImpl @Inject constructor(
    private val usersCollection: UsersCollection
) : UserRemoteDatasource {

    override fun upsert(user: User) = usersCollection.upsert(user.asDto())
    override fun getAll(): Flow<List<User>> = usersCollection.getAll().asDomain()
}

fun User.asDto(): UserDto =
    UserDto(
        id = id,
        username = username,
        email = email,
        profilePictureUrl = profilePictureUrl,
        isAnonymous = isAnonymous
    )

fun UserDto.asDomain(): User = User(
    id = id,
    username = username,
    email = email,
    profilePictureUrl = profilePictureUrl,
    isAnonymous = isAnonymous
)

fun List<UserDto>.asDomain(): List<User> = this.map { it.asDomain() }

fun Flow<List<UserDto>>.asDomain(): Flow<List<User>> = this.map { it.asDomain() }