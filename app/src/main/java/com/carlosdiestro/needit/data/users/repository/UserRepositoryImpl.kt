package com.carlosdiestro.needit.data.users.repository

import com.carlosdiestro.needit.core.mappers.asDto
import com.carlosdiestro.needit.data.users.datasources.UserRemoteDatasource
import com.carlosdiestro.needit.domain.users.User
import com.carlosdiestro.needit.domain.users.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDatasource: UserRemoteDatasource,
) : UserRepository {

    override suspend fun upsertUser(user: User) = userRemoteDatasource.upsert(user.asDto())
}