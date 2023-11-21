package com.carlosdiestro.needit.data.users

import com.carlosdiestro.needit.core.mappers.asDomain
import com.carlosdiestro.needit.core.mappers.asDto
import com.carlosdiestro.needit.core.mappers.asPreferences
import com.carlosdiestro.needit.domain.users.User
import com.carlosdiestro.needit.domain.users.repository.UserRepository
import com.carlosdiestro.remotedatabase.firestore.users.UserRemoteDatasource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userLocalDatasource: UserLocalDatasource,
    private val userRemoteDatasource: UserRemoteDatasource
) : UserRepository {

    override val user: Flow<User> = userLocalDatasource.user.asDomain()

    override suspend fun upsert(user: User) {
        userRemoteDatasource.upsert(user.asDto())
        userLocalDatasource.upsert(user.asPreferences())
    }

    override suspend fun clear() = userLocalDatasource.clear()
}