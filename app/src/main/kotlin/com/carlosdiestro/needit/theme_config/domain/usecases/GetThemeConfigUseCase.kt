package com.carlosdiestro.needit.theme_config.domain.usecases

import com.carlosdiestro.needit.theme_config.domain.ThemeConfig
import com.carlosdiestro.needit.theme_config.domain.repository.ThemeConfigRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThemeConfigUseCase @Inject constructor(
    private val repository: ThemeConfigRepository
) {
    operator fun invoke(): Flow<ThemeConfig> = repository.themeConfig
}