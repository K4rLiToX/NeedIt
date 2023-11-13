package com.carlosdiestro.needit.datastore

import com.carlosdiestro.needit.data.users.UserLocalDatasource
import com.carlosdiestro.needit.datastore.models.UserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class UserLocalDatasourceImpl @Inject constructor(
    private val preferences: NeedItPreferences
) : UserLocalDatasource {

    override val user: Flow<UserPreferences> = preferences.user

    override suspend fun upsert(user: UserPreferences) = preferences.upsertUser(user)

    override suspend fun clear() = preferences.clear()
}