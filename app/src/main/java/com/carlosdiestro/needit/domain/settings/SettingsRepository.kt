package com.carlosdiestro.needit.domain.settings

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val settings: Flow<Settings>
    suspend fun updateUseSystemScheme()
    suspend fun updateIsNightMode()
}