package com.carlosdiestro.needit.data.users.datasources

import com.carlosdiestro.needit.network.dtos.UserDto

interface UserRemoteDatasource {

    suspend fun upsert(dto: UserDto)
    suspend fun updateUser(dto: UserDto)
}