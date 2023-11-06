package com.carlosdiestro.needit.data.users.datasources

import com.carlosdiestro.needit.network.users.UserDto

interface UserRemoteDatasource {

    suspend fun upsert(dto: UserDto)
    suspend fun updateUser(dto: UserDto)
}