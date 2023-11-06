package com.carlosdiestro.needit.domain.preferences

import javax.inject.Inject

class UpdateIsNightModeUseCase @Inject constructor(
    private val repository: NeedItPreferencesRepository
) {
    suspend operator fun invoke() = repository.updateIsNightMode()
}