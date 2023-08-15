package com.carlosdiestro.needit.data.users.repository

import com.carlosdiestro.needit.core.di.IoDispatcher
import com.carlosdiestro.needit.core.mappers.toDto
import com.carlosdiestro.needit.data.users.datasources.UserRemoteDatasource
import com.carlosdiestro.needit.domain.users.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDatasource: UserRemoteDatasource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UserRepository {
    override suspend fun createUser(user: User) = withContext(dispatcher) {
        userRemoteDatasource.insertUser(user.toDto())
    }
}