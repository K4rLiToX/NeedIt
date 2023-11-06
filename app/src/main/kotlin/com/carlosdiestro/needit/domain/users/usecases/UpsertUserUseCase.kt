package com.carlosdiestro.needit.domain.users.usecases

import com.carlosdiestro.needit.domain.preferences.repository.NeedItPreferencesRepository
import com.carlosdiestro.needit.domain.users.User
import com.carlosdiestro.needit.domain.users.repository.UserRepository
import javax.inject.Inject

class UpsertUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val preferencesRepository: NeedItPreferencesRepository
) {

    suspend operator fun invoke(user: User) = kotlin.run {
        userRepository.upsertUser(user)
        preferencesRepository.updateUser(user)
    }
}