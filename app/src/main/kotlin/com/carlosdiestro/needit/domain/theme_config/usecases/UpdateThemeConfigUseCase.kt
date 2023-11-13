package com.carlosdiestro.needit.domain.theme_config.usecases

import com.carlosdiestro.needit.domain.theme_config.ThemeConfig
import com.carlosdiestro.needit.domain.theme_config.repository.ThemeConfigRepository
import javax.inject.Inject

class UpdateThemeConfigUseCase @Inject constructor(
    private val repository: ThemeConfigRepository
) {

    suspend operator fun invoke(themeConfig: ThemeConfig) =
        repository.update(themeConfig)
}