package com.carlosdiestro.needit.theme_config.domain.usecases

import com.carlosdiestro.needit.theme_config.domain.ThemeConfig
import com.carlosdiestro.needit.theme_config.domain.repository.ThemeConfigRepository
import javax.inject.Inject

class UpdateThemeConfigUseCase @Inject constructor(
    private val repository: ThemeConfigRepository
) {

    suspend operator fun invoke(themeConfig: ThemeConfig) =
        repository.update(themeConfig)
}