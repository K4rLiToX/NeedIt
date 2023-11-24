package com.carlosdiestro.app_settings.usecases

import com.carlosdiestro.app_settings.domain.ThemeConfig
import com.carlosdiestro.app_settings.domain.repository.ThemeConfigRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThemeConfigUseCase @Inject constructor(
    private val repository: ThemeConfigRepository
) {
    operator fun invoke(): Flow<ThemeConfig> = repository.themeConfig
}