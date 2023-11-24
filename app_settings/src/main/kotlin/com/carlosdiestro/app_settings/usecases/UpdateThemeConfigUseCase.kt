package com.carlosdiestro.app_settings.usecases

import com.carlosdiestro.app_settings.domain.ThemeConfig
import com.carlosdiestro.app_settings.domain.repository.ThemeConfigRepository
import javax.inject.Inject

class UpdateThemeConfigUseCase @Inject constructor(
    private val repository: ThemeConfigRepository
) {

    suspend operator fun invoke(themeConfig: ThemeConfig) =
        repository.update(themeConfig)
}