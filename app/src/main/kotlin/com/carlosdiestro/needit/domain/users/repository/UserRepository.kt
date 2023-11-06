package com.carlosdiestro.needit.domain.users.repository

import com.carlosdiestro.needit.domain.users.User

interface UserRepository {
    fun upsert(user: User)
}