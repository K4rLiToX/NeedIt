package com.carlosdiestro.needit.domain.preferences.repository

import com.carlosdiestro.needit.domain.preferences.ThemeConfig
import com.carlosdiestro.needit.domain.users.User
import kotlinx.coroutines.flow.Flow

interface NeedItPreferencesRepository {
    val user: Flow<User>
    val themeConfig: Flow<ThemeConfig>
    suspend fun updateUser(user: User)
    suspend fun updateThemeConfig(themeConfig: ThemeConfig)
    suspend fun clear()
}