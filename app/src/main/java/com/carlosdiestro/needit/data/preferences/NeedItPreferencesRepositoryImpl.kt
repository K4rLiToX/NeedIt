package com.carlosdiestro.needit.data.preferences

import com.carlosdiestro.needit.core.mappers.asDomain
import com.carlosdiestro.needit.core.mappers.asPreferences
import com.carlosdiestro.needit.domain.preferences.NeedItPreferencesRepository
import com.carlosdiestro.needit.domain.preferences.Settings
import com.carlosdiestro.needit.domain.users.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NeedItPreferencesRepositoryImpl @Inject constructor(
    private val datasource: NeedItPreferencesDatasource
) : NeedItPreferencesRepository {

    override val user: Flow<User> = datasource.user.asDomain()
    override val settings: Flow<Settings> = datasource.settings.asDomain()

    override suspend fun updateUser(user: User) = datasource.updateUser(user.asPreferences())

    override suspend fun updateUseSystemScheme() = datasource.updateUseSystemScheme()

    override suspend fun updateIsNightMode() = datasource.updateIsNightMode()

    override suspend fun clear() = datasource.clear()
}