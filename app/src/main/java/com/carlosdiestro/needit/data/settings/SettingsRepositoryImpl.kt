package com.carlosdiestro.needit.data.settings

import com.carlosdiestro.needit.core.mappers.asDomain
import com.carlosdiestro.needit.data.preferences.NeedItPreferencesDatasource
import com.carlosdiestro.needit.domain.settings.Settings
import com.carlosdiestro.needit.domain.settings.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val preferences: NeedItPreferencesDatasource
) : SettingsRepository {

    override val settings: Flow<Settings> = preferences.settings.asDomain()
    override suspend fun updateUseSystemScheme() = preferences.updateUseSystemScheme()

    override suspend fun updateIsNightMode() = preferences.updateIsNightMode()
}