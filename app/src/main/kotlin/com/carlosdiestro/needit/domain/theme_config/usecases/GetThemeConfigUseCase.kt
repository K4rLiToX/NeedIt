package com.carlosdiestro.needit.domain.theme_config.usecases

import com.carlosdiestro.needit.domain.theme_config.ThemeConfig
import com.carlosdiestro.needit.domain.theme_config.repository.ThemeConfigRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThemeConfigUseCase @Inject constructor(
    private val repository: ThemeConfigRepository
) {
    operator fun invoke(): Flow<ThemeConfig> = repository.themeConfig
}