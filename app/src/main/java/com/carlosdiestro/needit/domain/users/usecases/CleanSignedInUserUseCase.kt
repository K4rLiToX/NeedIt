package com.carlosdiestro.needit.domain.users.usecases

import com.carlosdiestro.needit.domain.users.repository.UserRepository
import javax.inject.Inject

class CleanSignedInUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun invoke() = userRepository.cleanSignedInUser()
}