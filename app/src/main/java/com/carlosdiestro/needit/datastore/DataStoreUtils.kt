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
    this.edit { prefs -> prefs.user = user }
}

internal suspend fun DataStore<Preferences>.updateUseSystemScheme() {
    this.edit { prefs -> prefs.useSystemScheme = !prefs.useSystemScheme }
}

internal suspend fun DataStore<Preferences>.updateIsNightMode() {
    this.edit { prefs -> prefs.isNightMode = !prefs.isNightMode }
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

internal var Preferences.user: UserPreferences
    get() = UserPreferences(
        id = userId,
        username = username,
        email = email,
        profilePictureUrl = profilePictureUrl,
        isAnonymous = isAnonymous
    )
    set(value) {
        this.userId = value.id
        this.username = value.username
        this.email = value.email
        this.profilePictureUrl = value.profilePictureUrl
        this.isAnonymous = value.isAnonymous
    }

internal var Preferences.settings: SettingsPreferences
    get() = SettingsPreferences(
        useSystemScheme = useSystemScheme,
        isNightMode = isNightMode
    )
    set(value) {
        this.useSystemScheme = value.useSystemScheme
        this.isNightMode = value.isNightMode
    }

internal var Preferences.userId: String
    get() = this[UserKeys.id].orEmpty()
    set(value) {
        this[UserKeys.id] = value
    }

internal var Preferences.username: String
    get() = this[UserKeys.username].orEmpty()
    set(value) {
        this[UserKeys.username] = value
    }

internal var Preferences.email: String
    get() = this[UserKeys.email].orEmpty()
    set(value) {
        this[UserKeys.email] = value
    }

internal var Preferences.profilePictureUrl: String
    get() = this[UserKeys.profilePictureUrl].orEmpty()
    set(value) {
        this[UserKeys.profilePictureUrl] = value
    }

internal var Preferences.isAnonymous: Boolean
    get() = this[UserKeys.isAnonymous] ?: true
    set(value) {
        this[UserKeys.isAnonymous] = value
    }

internal var Preferences.useSystemScheme: Boolean
    get() = this[SettingsKeys.useSystemScheme] ?: true
    set(value) {
        this[SettingsKeys.useSystemScheme] = value
    }

internal var Preferences.isNightMode: Boolean
    get() = this[SettingsKeys.isNightMode] ?: false
    set(value) {
        this[SettingsKeys.isNightMode] = value
    }

private operator fun Preferences.set(key: Preferences.Key<String>, value: String) {
    this[key] = value
}

private operator fun Preferences.set(key: Preferences.Key<Boolean>, value: Boolean) {
    this[key] = value
}