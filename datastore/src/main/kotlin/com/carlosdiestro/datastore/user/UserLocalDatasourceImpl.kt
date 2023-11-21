package com.carlosdiestro.datastore.user

import com.carlosdiestro.datastore.NeedItPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class UserLocalDatasourceImpl @Inject constructor(
    private val preferences: NeedItPreferences
) : UserLocalDatasource {

    override val user: Flow<UserPreferences> = preferences.user

    override suspend fun upsert(user: UserPreferences) = preferences.upsertUser(user)

    override suspend fun clear() = preferences.clear()
}