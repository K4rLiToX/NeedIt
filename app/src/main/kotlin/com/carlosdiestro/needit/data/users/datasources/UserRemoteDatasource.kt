package com.carlosdiestro.needit.data.users.datasources

import com.carlosdiestro.needit.network.users.UserDto

interface UserRemoteDatasource {
    fun upsert(dto: UserDto)
    fun updateUser(dto: UserDto)
}