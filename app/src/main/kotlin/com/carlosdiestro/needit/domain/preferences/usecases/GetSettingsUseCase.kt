package com.carlosdiestro.needit.domain.preferences.usecases

import com.carlosdiestro.needit.domain.preferences.repository.NeedItPreferencesRepository
import com.carlosdiestro.needit.domain.preferences.Settings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val repository: NeedItPreferencesRepository
) {
    operator fun invoke(): Flow<Settings> = repository.settings
}