package com.carlosdiestro.needit.core.mappers

import com.carlosdiestro.needit.domain.settings.Settings
import com.carlosdiestro.needit.datastore.models.SettingsPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun SettingsPreferences.toDomain(): Settings = Settings(
    useSystemScheme = useSystemScheme,
    isNightMode = isNightMode
)

fun Flow<SettingsPreferences>.toDomain(): Flow<Settings> = this.map { it.toDomain() }