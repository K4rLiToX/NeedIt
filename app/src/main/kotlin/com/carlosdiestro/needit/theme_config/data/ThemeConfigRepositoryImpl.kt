package com.carlosdiestro.needit.theme_config.data

import com.carlosdiestro.datastore.theme_config.ThemeConfigLocalDatasource
import com.carlosdiestro.needit.core.mappers.asDomain
import com.carlosdiestro.needit.core.mappers.asPreferences
import com.carlosdiestro.needit.theme_config.domain.ThemeConfig
import com.carlosdiestro.needit.theme_config.domain.repository.ThemeConfigRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ThemeConfigRepositoryImpl @Inject constructor(
    private val datasource: ThemeConfigLocalDatasource
) : ThemeConfigRepository {

    override val themeConfig: Flow<ThemeConfig> = datasource.themeConfig.asDomain()

    override suspend fun update(themeConfig: ThemeConfig) = datasource
        .update(themeConfig.asPreferences())
}