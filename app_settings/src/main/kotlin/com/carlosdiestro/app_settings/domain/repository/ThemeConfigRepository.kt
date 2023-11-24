package com.carlosdiestro.app_settings.domain.repository

import com.carlosdiestro.app_settings.domain.ThemeConfig
import kotlinx.coroutines.flow.Flow

interface ThemeConfigRepository {
    val themeConfig: Flow<ThemeConfig>
    suspend fun update(themeConfig: ThemeConfig)
}