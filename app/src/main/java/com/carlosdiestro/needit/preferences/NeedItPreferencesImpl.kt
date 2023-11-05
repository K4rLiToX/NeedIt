package com.carlosdiestro.needit.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.carlosdiestro.needit.data.preferences.NeedItPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val needItPreferencesName = "needit_preferences"
val Context.preferences: DataStore<Preferences> by preferencesDataStore(
    name = needItPreferencesName
)

class NeedItPreferencesImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : NeedItPreferences {

    private val preferences = context.preferences
    private val prefsData = preferences.data

    override val user: Flow<UserPreferences> = prefsData.map { prefs ->
        UserPreferences(
            id = prefs[userIdKey].orEmpty(),
            username = prefs[usernameKey].orEmpty(),
            email = prefs[userEmailKey].orEmpty(),
            profilePictureUrl = prefs[userProfilePictureKey].orEmpty(),
            isAnonymous = prefs[userIsAnonymousKey] ?: true
        )
    }
    override val settings: Flow<SettingsPreferences> = prefsData.map { prefs ->
        SettingsPreferences(
            useSystemScheme = prefs[settingsUseSystemSchemeKey] ?: true,
            isNightMode = prefs[settingsIsNightModeKey] ?: false
        )
    }

    override suspend fun updateUserInfo(userPreferences: UserPreferences) {
        preferences.edit { prefs ->
            prefs[userIdKey] = userPreferences.id
            prefs[usernameKey] = userPreferences.username
            prefs[userEmailKey] = userPreferences.email
            prefs[userProfilePictureKey] = userPreferences.profilePictureUrl
            prefs[userIsAnonymousKey] = userPreferences.isAnonymous
        }
    }

    override suspend fun updateUseSystemScheme() {
        preferences.edit { prefs ->
            val value = prefs[settingsUseSystemSchemeKey] ?: false
            prefs[settingsUseSystemSchemeKey] = !value
        }
    }

    override suspend fun updateIsNightMode() {
        preferences.edit { prefs ->
            val value = prefs[settingsIsNightModeKey] ?: true
            prefs[settingsIsNightModeKey] = !value
        }
    }

    override suspend fun cleanPreferences() {
        preferences.edit { prefs -> prefs.clear() }
    }

    companion object {
        private const val userIdKeyName = "user_id"
        private const val usernameKeyName = "user_username"
        private const val userEmailKeyName = "user_email"
        private const val userProfilePictureUrlKeyName = "user_profile_picture_url"
        private const val userIsAnonymousKeyName = "user_is_anonymous"
        private const val settingsUseSystemSchemeKeyName = "settings_use_system_scheme"
        private const val settingsIsNightModeKeyName = "settings_is_night_mode"
        private val userIdKey = stringPreferencesKey(userIdKeyName)
        private val usernameKey = stringPreferencesKey(usernameKeyName)
        private val userEmailKey = stringPreferencesKey(userEmailKeyName)
        private val userProfilePictureKey = stringPreferencesKey(userProfilePictureUrlKeyName)
        private val userIsAnonymousKey = booleanPreferencesKey(userIsAnonymousKeyName)
        private val settingsUseSystemSchemeKey =
            booleanPreferencesKey(settingsUseSystemSchemeKeyName)
        private val settingsIsNightModeKey = booleanPreferencesKey(settingsIsNightModeKeyName)
    }
}