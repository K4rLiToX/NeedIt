package com.carlosdiestro.needit.core.mappers

import com.carlosdiestro.auth.UserAuth
import com.carlosdiestro.user.domain.User

fun UserAuth.asDomain(): User = User(
    id = userId,
    username = username,
    email = email,
    profilePictureUrl = profilePictureUrl.orEmpty(),
    isAnonymous = isAnonymous
)