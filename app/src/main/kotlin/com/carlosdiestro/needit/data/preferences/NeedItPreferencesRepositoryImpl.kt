package com.carlosdiestro.needit.data.preferences

import com.carlosdiestro.needit.core.mappers.asDomain
import com.carlosdiestro.needit.core.mappers.asPreferences
import com.carlosdiestro.needit.domain.preferences.ThemeConfig
import com.carlosdiestro.needit.domain.preferences.repository.NeedItPreferencesRepository
import com.carlosdiestro.needit.domain.users.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class NeedItPreferencesRepositoryImpl @Inject constructor(
    private val datasource: NeedItPreferencesDatasource
) : NeedItPreferencesRepository {

    override val user: Flow<User> = datasource.user.asDomain()

    override val themeConfig: Flow<ThemeConfig> = datasource.settings.asDomain()

    override suspend fun updateUser(user: User) = datasource.updateUser(user.asPreferences())

    override suspend fun updateThemeConfig(themeConfig: ThemeConfig) = datasource
        .updateThemeConfig(themeConfig.asPreferences())

    override suspend fun clear() = datasource.clear()
}