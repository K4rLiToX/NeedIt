package com.carlosdiestro.needit.domain.users.usecases

import com.carlosdiestro.needit.domain.preferences.NeedItPreferencesRepository
import com.carlosdiestro.needit.domain.users.User
import com.carlosdiestro.needit.domain.users.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSignedInUserUseCase @Inject constructor(
    private val preferencesRepository: NeedItPreferencesRepository
) {

    operator fun invoke(): Flow<User> = preferencesRepository.user
}