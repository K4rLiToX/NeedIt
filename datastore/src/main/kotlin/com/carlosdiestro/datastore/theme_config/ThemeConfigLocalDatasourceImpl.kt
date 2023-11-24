package com.carlosdiestro.datastore.theme_config

import com.carlosdiestro.app_settings.data.ThemeConfigLocalDatasource
import com.carlosdiestro.app_settings.domain.ThemeConfig
import com.carlosdiestro.datastore.NeedItPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class ThemeConfigLocalDatasourceImpl @Inject constructor(
    private val preferences: NeedItPreferences
) : ThemeConfigLocalDatasource {

    override val themeConfig: Flow<ThemeConfig> = preferences.themeConfig.asDomain()

    override suspend fun update(themeConfig: ThemeConfig) = preferences
        .updateThemeConfig(themeConfig.asPreferences())
}

fun ThemeConfigPreferences.asDomain(): ThemeConfig = ThemeConfig.entries[this.ordinal]
fun Flow<ThemeConfigPreferences>.asDomain(): Flow<ThemeConfig> = this.map { it.asDomain() }

fun ThemeConfig.asPreferences(): ThemeConfigPreferences =
    ThemeConfigPreferences.entries[this.ordinal]