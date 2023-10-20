package com.carlosdiestro.needit.core.mappers

import com.carlosdiestro.needit.auth.UserAuth
import com.carlosdiestro.needit.domain.users.User
import com.carlosdiestro.needit.network.dtos.UserDto
import com.carlosdiestro.needit.preferences.UserPrefs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun User.asDto(): UserDto = UserDto(
    id = id,
    username = username,
    email = email,
    profilePictureUrl = profilePictureUrl
)

fun UserAuth.asDomain(): User = User(
    id = userId,
    username = username,
    email = email,
    profilePictureUrl = profilePictureUrl.orEmpty()
)

fun User.toPreferences(): UserPrefs = UserPrefs(
    id = id,
    username = username,
    email = email,
    profilePictureUrl = profilePictureUrl
)

fun UserPrefs.asDomain(): User = User(
    id = id,
    username = username,
    email = email,
    profilePictureUrl = profilePictureUrl
)

fun Flow<UserPrefs>.asDomain(): Flow<User> = this.map { it.asDomain() }