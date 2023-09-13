package com.carlosdiestro.needit.domain.users.usecases

import com.carlosdiestro.needit.domain.users.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsUserGuestUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(): Flow<Boolean> = userRepository.isUserGuest
}