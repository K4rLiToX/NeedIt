package com.carlosdiestro.needit.core.mappers

import com.carlosdiestro.needit.domain.settings.Settings
import com.carlosdiestro.needit.datastore.models.SettingsPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun SettingsPreferences.asDomain(): Settings = Settings(
    useSystemScheme = useSystemScheme,
    isNightMode = isNightMode
)

fun Flow<SettingsPreferences>.asDomain(): Flow<Settings> = this.map { it.asDomain() }