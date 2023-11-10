package com.carlosdiestro.needit.datastore

import com.carlosdiestro.needit.data.preferences.NeedItPreferencesDatasource
import com.carlosdiestro.needit.datastore.models.ThemeConfigPreferences
import com.carlosdiestro.needit.datastore.models.UserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class NeedItPreferencesDatasourceImpl @Inject constructor(
    private val preferences: NeedItPreferences
) : NeedItPreferencesDatasource {

    override val user: Flow<UserPreferences> = preferences.user

    override val settings: Flow<ThemeConfigPreferences> = preferences.settings

    override suspend fun updateUser(user: UserPreferences) = preferences.updateUser(user)

    override suspend fun updateThemeConfig(themeConfig: ThemeConfigPreferences) =
        preferences.updateThemeConfig(themeConfig)

    override suspend fun clear() = preferences.clear()
}