package com.carlosdiestro.needit.data.users

import com.carlosdiestro.needit.core.mappers.asDto
import com.carlosdiestro.needit.domain.users.User
import com.carlosdiestro.needit.domain.users.repository.UserRepository
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userRemoteDatasource: UserRemoteDatasource,
) : UserRepository {

    override fun upsert(user: User) = userRemoteDatasource.upsert(user.asDto())
}