package com.carlosdiestro.user.data

import com.carlosdiestro.user.data.datasource.UserLocalDatasource
import com.carlosdiestro.user.data.datasource.UserRemoteDatasource
import com.carlosdiestro.user.domain.User
import com.carlosdiestro.user.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDatasource: UserLocalDatasource,
    private val userRemoteDatasource: UserRemoteDatasource
) : UserRepository {

    override val currentUser: Flow<User> = userLocalDatasource.user

    override suspend fun getCurrentUser(): User = currentUser.first()

    override suspend fun getCurrentUserId(): String = getCurrentUser().id

    override suspend fun upsert(user: User) {
        userRemoteDatasource.upsert(user)
        userLocalDatasource.upsert(user)
    }

    override fun getAll(): Flow<List<User>> = combine(
        currentUser,
        userRemoteDatasource.getAll()
    ) { signedInUser, users ->
        users.filter { it.id != signedInUser.id }
    }

    override suspend fun clear() = userLocalDatasource.clear()
}