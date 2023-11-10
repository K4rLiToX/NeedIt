package com.carlosdiestro.needit.data.preferences

import com.carlosdiestro.needit.datastore.models.ThemeConfigPreferences
import com.carlosdiestro.needit.datastore.models.UserPreferences
import kotlinx.coroutines.flow.Flow

interface NeedItPreferencesDatasource {
    val user: Flow<UserPreferences>
    val settings: Flow<ThemeConfigPreferences>
    suspend fun updateUser(user: UserPreferences)
    suspend fun updateThemeConfig(themeConfig: ThemeConfigPreferences)
    suspend fun clear()
}