package com.carlosdiestro.needit.data.users

import com.carlosdiestro.needit.framework.network.users.UserDto

interface UserRemoteDatasource {
    fun upsert(user: UserDto)
}