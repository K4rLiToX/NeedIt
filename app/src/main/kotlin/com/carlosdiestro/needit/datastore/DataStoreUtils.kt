package com.carlosdiestro.needit.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.carlosdiestro.needit.datastore.models.SettingsPreferences
import com.carlosdiestro.needit.datastore.models.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal object UserKeys {
    internal val id = stringPreferencesKey("user_id")
    internal val username = stringPreferencesKey("user_username")
    internal val email = stringPreferencesKey("user_email")
    internal val profilePictureUrl = stringPreferencesKey("user_profile_picture_url")
    internal val isAnonymous = booleanPreferencesKey("user_is_anonymous")
}

internal object SettingsKeys {
    internal val useSystemScheme = booleanPreferencesKey("settings_use_system_scheme")
    internal val isNightMode = booleanPreferencesKey("settings_is_night_mode")
}

internal suspend fun DataStore<Preferences>.updateUser(user: UserPreferences) {
    this.edit { prefs ->
        prefs[UserKeys.id] = user.id
        prefs[UserKeys.username] = user.username
        prefs[UserKeys.email] = user.email
        prefs[UserKeys.profilePictureUrl] = user.profilePictureUrl
        prefs[UserKeys.isAnonymous] = user.isAnonymous
    }
}

internal suspend fun DataStore<Preferences>.updateUseSystemScheme() {
    this.edit { prefs ->
        prefs[SettingsKeys.useSystemScheme] = !prefs.useSystemScheme
    }
}

internal suspend fun DataStore<Preferences>.updateIsNightMode() {
    this.edit { prefs ->
        prefs[SettingsKeys.isNightMode] = !prefs.isNightMode
    }
}

internal suspend fun DataStore<Preferences>.clear() {
    this.edit { prefs -> prefs.clear() }
}

internal val DataStore<Preferences>.user: Flow<UserPreferences>
    get() = this.data.user

internal val DataStore<Preferences>.settings: Flow<SettingsPreferences>
    get() = this.data.settings

internal val Flow<Preferences>.user: Flow<UserPreferences>
    get() = this.map { it.user }

internal val Flow<Preferences>.settings: Flow<SettingsPreferences>
    get() = this.map { it.settings }

internal val Preferences.user: UserPreferences
    get() = UserPreferences(
        id = userId,
        username = username,
        email = email,
        profilePictureUrl = profilePictureUrl,
        isAnonymous = isAnonymous
    )

internal val Preferences.settings: SettingsPreferences
    get() = SettingsPreferences(
        useSystemScheme = useSystemScheme,
        isNightMode = isNightMode
    )

internal val Preferences.userId: String
    get() = this[UserKeys.id].orEmpty()

internal val Preferences.username: String
    get() = this[UserKeys.username].orEmpty()

internal val Preferences.email: String
    get() = this[UserKeys.email].orEmpty()

internal val Preferences.profilePictureUrl: String
    get() = this[UserKeys.profilePictureUrl].orEmpty()

internal val Preferences.isAnonymous: Boolean
    get() = this[UserKeys.isAnonymous] ?: true

internal val Preferences.useSystemScheme: Boolean
    get() = this[SettingsKeys.useSystemScheme] ?: true

internal val Preferences.isNightMode: Boolean
    get() = this[SettingsKeys.isNightMode] ?: false