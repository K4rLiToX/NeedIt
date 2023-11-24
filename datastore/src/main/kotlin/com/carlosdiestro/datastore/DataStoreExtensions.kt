package com.carlosdiestro.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.carlosdiestro.datastore.theme_config.ThemeConfigPreferences
import com.carlosdiestro.datastore.theme_config.asPreferences
import com.carlosdiestro.datastore.theme_config.asValue
import com.carlosdiestro.datastore.user.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal object UserKeys {
    internal val id = stringPreferencesKey("user_id")
    internal val username = stringPreferencesKey("user_username")
    internal val email = stringPreferencesKey("user_email")
    internal val profilePictureUrl = stringPreferencesKey("user_profile_picture_url")
    internal val isAnonymous = booleanPreferencesKey("user_is_anonymous")
}

internal object ThemeConfigKeys {
    internal val themeConfig = intPreferencesKey("theme_config")
}

internal suspend fun DataStore<Preferences>.upsertUser(user: UserPreferences) {
    this.edit { prefs ->
        prefs[UserKeys.id] = user.id
        prefs[UserKeys.username] = user.username
        prefs[UserKeys.email] = user.email
        prefs[UserKeys.profilePictureUrl] = user.profilePictureUrl
        prefs[UserKeys.isAnonymous] = user.isAnonymous
    }
}

internal suspend fun DataStore<Preferences>.updateThemeConfig(themeConfig: ThemeConfigPreferences) {
    this.edit { prefs ->
        prefs[ThemeConfigKeys.themeConfig] = themeConfig.asValue()
    }
}

internal suspend fun DataStore<Preferences>.clear() {
    this.edit { prefs -> prefs.clear() }
}

internal val DataStore<Preferences>.user: Flow<UserPreferences>
    get() = this.data.user

internal val DataStore<Preferences>.themeConfig: Flow<ThemeConfigPreferences>
    get() = this.data.themeConfig

internal val Flow<Preferences>.user: Flow<UserPreferences>
    get() = this.map { it.user }

internal val Flow<Preferences>.themeConfig: Flow<ThemeConfigPreferences>
    get() = this.map { it.themeConfig }

internal val Preferences.user: UserPreferences
    get() = UserPreferences(
        id = userId,
        username = username,
        email = email,
        profilePictureUrl = profilePictureUrl,
        isAnonymous = isAnonymous
    )

internal val Preferences.themeConfig: ThemeConfigPreferences
    get() = this[ThemeConfigKeys.themeConfig].asPreferences()

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