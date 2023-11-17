package com.carlosdiestro.needit.domain.users.usecases

import com.carlosdiestro.needit.domain.users.User
import com.carlosdiestro.needit.domain.users.repository.UserRepository
import javax.inject.Inject

class UpsertUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(user: User) = kotlin.run {
        userRepository.upsert(user)
    }
}