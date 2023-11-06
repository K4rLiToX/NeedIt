package com.carlosdiestro.needit.domain.users.usecases

import com.carlosdiestro.needit.domain.preferences.NeedItPreferencesRepository
import javax.inject.Inject

class CleanSignedInUserUseCase @Inject constructor(
    private val preferencesRepository: NeedItPreferencesRepository
) {
    suspend fun invoke() = preferencesRepository.clear()
}