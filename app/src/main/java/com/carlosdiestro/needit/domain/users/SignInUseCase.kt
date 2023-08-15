package com.carlosdiestro.needit.domain.users

import com.carlosdiestro.needit.data.users.UserRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(user: User) = userRepository.createUser(user)
}