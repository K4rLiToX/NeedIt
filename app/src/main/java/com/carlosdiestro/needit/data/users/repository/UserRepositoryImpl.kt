package com.carlosdiestro.needit.data.users.repository

import com.carlosdiestro.needit.core.di.IoDispatcher
import com.carlosdiestro.needit.core.mappers.asDomain
import com.carlosdiestro.needit.core.mappers.asDto
import com.carlosdiestro.needit.core.mappers.toPreferences
import com.carlosdiestro.needit.data.users.datasources.UserLocalDatasource
import com.carlosdiestro.needit.data.users.datasources.UserRemoteDatasource
import com.carlosdiestro.needit.domain.users.User
import com.carlosdiestro.needit.domain.users.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDatasource: UserRemoteDatasource,
    private val userLocalDatasource: UserLocalDatasource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UserRepository {

    override val user: Flow<User> = userLocalDatasource.userInfo.asDomain()

    override suspend fun upsertUser(user: User) = withContext(dispatcher) {
        userRemoteDatasource.upsert(user.asDto())
        userLocalDatasource.updateUserInfo(user.toPreferences())
    }

    override suspend fun updateUser(user: User) = withContext(dispatcher) {
        userRemoteDatasource.updateUser(user.asDto())
        userLocalDatasource.updateUserInfo(user.toPreferences())
    }
}