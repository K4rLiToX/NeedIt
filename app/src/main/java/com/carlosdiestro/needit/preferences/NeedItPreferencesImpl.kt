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

    override val user: Flow<UserPreferences> = preferences.user

    override val settings: Flow<SettingsPreferences> = preferences.settings

    override suspend fun updateUserInfo(userPreferences: UserPreferences) {
        preferences.edit { prefs ->
            prefs.user = userPreferences
        }
    }

    override suspend fun updateUseSystemScheme() {
        preferences.edit { prefs ->
            prefs.useSystemScheme = !prefs.useSystemScheme
        }
    }

    override suspend fun updateIsNightMode() {
        preferences.edit { prefs ->
            prefs.isNightMode = !prefs.isNightMode
        }
    }

    override suspend fun cleanPreferences() {
        preferences.edit { prefs -> prefs.clear() }
    }
}

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