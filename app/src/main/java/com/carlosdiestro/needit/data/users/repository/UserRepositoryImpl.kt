package com.carlosdiestro.needit.data.users.repository

import com.carlosdiestro.needit.core.di.IoDispatcher
import com.carlosdiestro.needit.core.mappers.toDomain
import com.carlosdiestro.needit.core.mappers.toDto
import com.carlosdiestro.needit.core.mappers.toPreferences
import com.carlosdiestro.needit.data.users.datasources.UserLocalDatasource
import com.carlosdiestro.needit.data.users.datasources.UserRemoteDatasource
import com.carlosdiestro.needit.domain.users.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDatasource: UserRemoteDatasource,
    private val userLocalDatasource: UserLocalDatasource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UserRepository {

    override val user: Flow<User> = userLocalDatasource.userInfo.toDomain()

    override val isUserGuest: Flow<Boolean> = userLocalDatasource.isUserGuest
    override suspend fun createUser(user: User) = withContext(dispatcher) {
        userRemoteDatasource.insertUser(user.toDto())
        userLocalDatasource.run {
            updateUserInfo(user.toPreferences())
            updateIsUserGuest()
        }
    }
}