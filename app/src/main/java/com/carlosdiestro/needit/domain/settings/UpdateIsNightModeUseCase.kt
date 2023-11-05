package com.carlosdiestro.needit.domain.settings

import javax.inject.Inject

class UpdateIsNightModeUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke() = repository.updateIsNightMode()
}