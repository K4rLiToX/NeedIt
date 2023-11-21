package com.carlosdiestro.user.usecases

import com.carlosdiestro.user.domain.UserRepository
import javax.inject.Inject

class CleanSignedInUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun invoke() = userRepository.clear()
}