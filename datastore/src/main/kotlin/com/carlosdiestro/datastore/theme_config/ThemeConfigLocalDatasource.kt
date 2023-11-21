package com.carlosdiestro.datastore.theme_config

import kotlinx.coroutines.flow.Flow

interface ThemeConfigLocalDatasource {

    val themeConfig: Flow<ThemeConfigPreferences>

    suspend fun update(themeConfig: ThemeConfigPreferences)
}