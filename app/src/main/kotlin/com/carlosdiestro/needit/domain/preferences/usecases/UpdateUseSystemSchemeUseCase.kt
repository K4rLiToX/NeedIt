package com.carlosdiestro.needit.domain.preferences.usecases

import com.carlosdiestro.needit.domain.preferences.repository.NeedItPreferencesRepository
import javax.inject.Inject

class UpdateUseSystemSchemeUseCase @Inject constructor(
    private val repository: NeedItPreferencesRepository
) {
    suspend operator fun invoke() = repository.updateUseSystemScheme()
}