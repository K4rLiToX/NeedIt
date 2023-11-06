package com.carlosdiestro.needit.datastore

import com.carlosdiestro.needit.data.preferences.NeedItPreferencesDatasource
import com.carlosdiestro.needit.datastore.models.SettingsPreferences
import com.carlosdiestro.needit.datastore.models.UserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class NeedItPreferencesDatasourceImpl @Inject constructor(
    private val preferences: NeedItPreferences
) : NeedItPreferencesDatasource {

    override val user: Flow<UserPreferences> = preferences.user

    override val settings: Flow<SettingsPreferences> = preferences.settings

    override suspend fun updateUser(user: UserPreferences) = preferences.updateUser(user)

    override suspend fun updateUseSystemScheme() = preferences.updateUseSystemScheme()

    override suspend fun updateIsNightMode() = preferences.updateIsNightMode()

    override suspend fun clear() = preferences.clear()
}