package com.carlosdiestro.needit.domain.preferences

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val repository: NeedItPreferencesRepository
) {
    operator fun invoke(): Flow<Settings> = repository.settings
}