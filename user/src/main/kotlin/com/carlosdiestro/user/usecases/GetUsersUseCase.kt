package com.carlosdiestro.user.usecases

import com.carlosdiestro.user.domain.User
import com.carlosdiestro.user.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {

    operator fun invoke(): Flow<List<User>> = repository.getAll()
}