package com.carlosdiestro.datastore.theme_config

import com.carlosdiestro.datastore.NeedItPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ThemeConfigLocalDatasourceImpl @Inject constructor(
    private val preferences: NeedItPreferences
) : ThemeConfigLocalDatasource {

    override val themeConfig: Flow<ThemeConfigPreferences> = preferences.themeConfig

    override suspend fun update(themeConfig: ThemeConfigPreferences) = preferences
        .updateThemeConfig(themeConfig)
}