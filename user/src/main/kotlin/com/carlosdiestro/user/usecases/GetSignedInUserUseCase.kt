package com.carlosdiestro.user.usecases

import com.carlosdiestro.user.domain.User
import com.carlosdiestro.user.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSignedInUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(): Flow<User> = userRepository.user
}