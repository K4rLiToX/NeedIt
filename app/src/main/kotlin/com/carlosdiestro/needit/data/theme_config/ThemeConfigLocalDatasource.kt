package com.carlosdiestro.needit.data.theme_config

import com.carlosdiestro.needit.datastore.models.ThemeConfigPreferences
import kotlinx.coroutines.flow.Flow

interface ThemeConfigLocalDatasource {

    val themeConfig: Flow<ThemeConfigPreferences>

    suspend fun update(themeConfig: ThemeConfigPreferences)
}