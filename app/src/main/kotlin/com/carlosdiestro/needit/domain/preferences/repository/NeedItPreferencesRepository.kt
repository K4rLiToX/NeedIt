package com.carlosdiestro.needit.domain.preferences.repository

import com.carlosdiestro.needit.domain.preferences.Settings
import com.carlosdiestro.needit.domain.users.User
import kotlinx.coroutines.flow.Flow

interface NeedItPreferencesRepository {
    val user: Flow<User>
    val settings: Flow<Settings>
    suspend fun updateUser(user: User)
    suspend fun updateUseSystemScheme()
    suspend fun updateIsNightMode()
    suspend fun clear()
}