package com.carlosdiestro.needit.domain.preferences.usecases

import com.carlosdiestro.needit.domain.preferences.ThemeConfig
import com.carlosdiestro.needit.domain.preferences.repository.NeedItPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThemeConfigUseCase @Inject constructor(
    private val repository: NeedItPreferencesRepository
) {
    operator fun invoke(): Flow<ThemeConfig> = repository.themeConfig
}