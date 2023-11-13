package com.carlosdiestro.needit.data.theme_config

import com.carlosdiestro.needit.core.mappers.asDomain
import com.carlosdiestro.needit.core.mappers.asPreferences
import com.carlosdiestro.needit.domain.theme_config.ThemeConfig
import com.carlosdiestro.needit.domain.theme_config.repository.ThemeConfigRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ThemeConfigRepositoryImpl @Inject constructor(
    private val datasource: ThemeConfigLocalDatasource
) : ThemeConfigRepository {

    override val themeConfig: Flow<ThemeConfig> = datasource.themeConfig.asDomain()

    override suspend fun update(themeConfig: ThemeConfig) = datasource
        .update(themeConfig.asPreferences())
}