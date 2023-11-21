package com.carlosdiestro.user.data.datasource

import com.carlosdiestro.user.domain.User

interface UserRemoteDatasource {
    fun upsert(user: User)
}