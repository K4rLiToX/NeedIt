package com.carlosdiestro.needit.data.users

import com.carlosdiestro.needit.network.dtos.UserDto

interface UserRemoteDatasource {

    suspend fun insertUser(dto: UserDto)
}