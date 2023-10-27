package com.carlosdiestro.needit.domain.users.usecases

import com.carlosdiestro.needit.domain.users.User
import com.carlosdiestro.needit.domain.users.repository.UserRepository
import javax.inject.Inject

class CreateNewUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(user: User) = userRepository.createUser(user)
}