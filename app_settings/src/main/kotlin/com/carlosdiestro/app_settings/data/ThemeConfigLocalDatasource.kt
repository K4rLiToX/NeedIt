package com.carlosdiestro.app_settings.data

import com.carlosdiestro.app_settings.domain.ThemeConfig
import kotlinx.coroutines.flow.Flow

interface ThemeConfigLocalDatasource {

    val themeConfig: Flow<ThemeConfig>

    suspend fun update(themeConfig: ThemeConfig)
}