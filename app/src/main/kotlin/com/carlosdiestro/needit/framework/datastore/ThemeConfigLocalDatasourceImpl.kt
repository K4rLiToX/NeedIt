package com.carlosdiestro.needit.framework.datastore

import com.carlosdiestro.needit.data.theme_config.ThemeConfigLocalDatasource
import com.carlosdiestro.needit.framework.datastore.models.ThemeConfigPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ThemeConfigLocalDatasourceImpl @Inject constructor(
    private val preferences: NeedItPreferences
) : ThemeConfigLocalDatasource {

    override val themeConfig: Flow<ThemeConfigPreferences> = preferences.themeConfig

    override suspend fun update(themeConfig: ThemeConfigPreferences) = preferences
        .updateThemeConfig(themeConfig)
}