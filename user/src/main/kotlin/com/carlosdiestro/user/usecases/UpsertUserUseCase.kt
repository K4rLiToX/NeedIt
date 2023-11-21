package com.carlosdiestro.user.usecases

import com.carlosdiestro.user.domain.User
import com.carlosdiestro.user.domain.UserRepository
import javax.inject.Inject

class UpsertUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(user: User) = kotlin.run {
        userRepository.upsert(user)
    }
}