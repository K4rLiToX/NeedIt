package com.carlosdiestro.needit.domain.preferences.usecases

import com.carlosdiestro.needit.domain.preferences.ThemeConfig
import com.carlosdiestro.needit.domain.preferences.repository.NeedItPreferencesRepository
import javax.inject.Inject

class UpdateThemeConfigUseCase @Inject constructor(
    private val repository: NeedItPreferencesRepository
) {

    suspend operator fun invoke(themeConfig: ThemeConfig) =
        repository.updateThemeConfig(themeConfig)
}