package com.carlosdiestro.datastore.user

import com.carlosdiestro.datastore.NeedItPreferences
import com.carlosdiestro.user.data.datasource.UserLocalDatasource
import com.carlosdiestro.user.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class UserLocalDatasourceImpl @Inject constructor(
    private val preferences: NeedItPreferences
) : UserLocalDatasource {

    override val user: Flow<User> = preferences.user.asDomain()

    override suspend fun upsert(user: User) = preferences.upsertUser(user.asPreferences())

    override suspend fun clear() = preferences.clear()
}

fun User.asPreferences(): UserPreferences = UserPreferences(
    id = id,
    username = username,
    email = email,
    profilePictureUrl = profilePictureUrl,
    isAnonymous = isAnonymous
)

fun UserPreferences.asDomain(): User = User(
    id = id,
    username = username,
    email = email,
    profilePictureUrl = profilePictureUrl,
    isAnonymous = isAnonymous
)

fun Flow<UserPreferences>.asDomain(): Flow<User> = this.map { it.asDomain() }