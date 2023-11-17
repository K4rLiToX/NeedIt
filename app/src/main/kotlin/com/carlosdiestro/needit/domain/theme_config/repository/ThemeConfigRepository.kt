package com.carlosdiestro.needit.domain.theme_config.repository

import com.carlosdiestro.needit.domain.theme_config.ThemeConfig
import kotlinx.coroutines.flow.Flow

interface ThemeConfigRepository {
    val themeConfig: Flow<ThemeConfig>
    suspend fun update(themeConfig: ThemeConfig)
}