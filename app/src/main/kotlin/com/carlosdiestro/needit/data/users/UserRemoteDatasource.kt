package com.carlosdiestro.needit.data.users

import com.carlosdiestro.needit.network.users.UserDto

interface UserRemoteDatasource {
    fun upsert(user: UserDto)
}