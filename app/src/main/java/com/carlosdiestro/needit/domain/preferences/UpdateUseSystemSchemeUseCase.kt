package com.carlosdiestro.needit.domain.preferences

import javax.inject.Inject

class UpdateUseSystemSchemeUseCase @Inject constructor(
    private val repository: NeedItPreferencesRepository
) {
    suspend operator fun invoke() = repository.updateUseSystemScheme()
}