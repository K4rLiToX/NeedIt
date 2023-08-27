package com.carlosdiestro.needit.core.mappers

import com.carlosdiestro.needit.auth.UserAuth
import com.carlosdiestro.needit.domain.users.User
import com.carlosdiestro.needit.network.dtos.UserDto
import com.carlosdiestro.needit.preferences.UserPrefs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun User.toDto(): UserDto = UserDto(
    id = id,
    username = username,
    email = email,
    profilePictureUrl = profilePictureUrl
)

fun UserAuth.toDomain(): User = User(
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

fun UserPrefs.toDomain(): User = User(
    id = id,
    username = username,
    email = email,
    profilePictureUrl = profilePictureUrl
)

fun Flow<UserPrefs>.toDomain(): Flow<User> = this.map { it.toDomain() }