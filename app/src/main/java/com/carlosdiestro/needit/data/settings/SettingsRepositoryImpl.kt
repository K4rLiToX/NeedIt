package com.carlosdiestro.needit.data.settings

import com.carlosdiestro.needit.core.mappers.toDomain
import com.carlosdiestro.needit.data.preferences.NeedItPreferences
import com.carlosdiestro.needit.domain.settings.Settings
import com.carlosdiestro.needit.domain.settings.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    preferences: NeedItPreferences
) : SettingsRepository {

    override val settings: Flow<Settings> = preferences.settings.toDomain()
}