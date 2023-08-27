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

    override val isUserGuest: Flow<Boolean> = prefsData.map { prefs ->
        prefs[isUserNameKey] ?: true
    }

    override suspend fun updateIsUserGuest() {
        preferences.edit { prefs ->
            prefs[isUserNameKey] = false
        }
    }

    override val userInfo: Flow<UserPrefs> = prefsData.map { prefs ->
        UserPrefs(
            id = prefs[userIdKey].orEmpty(),
            username = prefs[usernameKey].orEmpty(),
            email = prefs[userEmailKey].orEmpty(),
            profilePictureUrl = prefs[userProfilePictureKey].orEmpty()
        )
    }

    override suspend fun updateUserInfo(userPrefs: UserPrefs) {
        preferences.edit { prefs ->
            prefs[userIdKey] = userPrefs.id
            prefs[usernameKey] = userPrefs.username
            prefs[userEmailKey] = userPrefs.email
            prefs[userProfilePictureKey] = userPrefs.profilePictureUrl
        }
    }

    companion object {
        private const val isUserGuestKeyName = "user_type"
        private val isUserNameKey = booleanPreferencesKey(isUserGuestKeyName)

        private const val userIdKeyName = "user_id"
        private const val usernameKeyName = "user_username"
        private const val userEmailKeyName = "user_email"
        private const val userProfilePictureUrlKeyName = "user_profile_picture_url"
        private val userIdKey = stringPreferencesKey(userIdKeyName)
        private val usernameKey = stringPreferencesKey(usernameKeyName)
        private val userEmailKey = stringPreferencesKey(userEmailKeyName)
        private val userProfilePictureKey = stringPreferencesKey(userProfilePictureUrlKeyName)
    }
}