package com.carlosdiestro.app_settings.data

import com.carlosdiestro.app_settings.domain.ThemeConfig
import com.carlosdiestro.app_settings.domain.repository.ThemeConfigRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemeConfigRepositoryImpl @Inject constructor(
    private val datasource: ThemeConfigLocalDatasource
) : ThemeConfigRepository {

    override val themeConfig: Flow<ThemeConfig> = datasource.themeConfig

    override suspend fun update(themeConfig: ThemeConfig) = datasource.update(themeConfig)
}