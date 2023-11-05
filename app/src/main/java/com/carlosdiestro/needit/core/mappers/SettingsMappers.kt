package com.carlosdiestro.needit.core.mappers

import com.carlosdiestro.needit.domain.settings.Settings
import com.carlosdiestro.needit.preferences.SettingsPrefs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun SettingsPrefs.toDomain(): Settings = Settings(
    useSystemScheme = useSystemScheme,
    isNightMode = isNightMode
)

fun Flow<SettingsPrefs>.toDomain(): Flow<Settings> = this.map { it.toDomain() }