package com.carlosdiestro.needit.core.mappers

import com.carlosdiestro.datastore.user.UserPreferences
import com.carlosdiestro.needit.auth.UserAuth
import com.carlosdiestro.needit.domain.users.User
import com.carlosdiestro.remotedatabase.firestore.users.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun User.asDto(): UserDto =
    UserDto(
        id = id,
        username = username,
        email = email,
        profilePictureUrl = profilePictureUrl,
        isAnonymous = isAnonymous
    )

fun UserAuth.asDomain(): User = User(
    id = userId,
    username = username,
    email = email,
    profilePictureUrl = profilePictureUrl.orEmpty(),
    isAnonymous = isAnonymous
)

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