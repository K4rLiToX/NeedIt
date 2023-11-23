package com.carlosdiestro.needit.theme_config.domain.repository

import com.carlosdiestro.needit.theme_config.domain.ThemeConfig
import kotlinx.coroutines.flow.Flow

interface ThemeConfigRepository {
    val themeConfig: Flow<ThemeConfig>
    suspend fun update(themeConfig: ThemeConfig)
}