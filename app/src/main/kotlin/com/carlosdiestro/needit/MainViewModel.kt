package com.carlosdiestro.needit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.auth.AuthClient
import com.carlosdiestro.needit.domain.preferences.usecases.GetSettingsUseCase
import com.carlosdiestro.needit.domain.users.usecases.GetSignedInUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getSettings: GetSettingsUseCase,
    getSignedInUser: GetSignedInUserUseCase,
    authClient: AuthClient
) : ViewModel() {

    val isSignedIn = authClient.signedInUser != null

    val profilePictureUrl: StateFlow<String> = getSignedInUser()
        .map { user ->
            user.profilePictureUrl
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ""
        )

    val state: StateFlow<MainState> = getSettings()
        .map {
                settings ->
            MainState.Success(
                value = ThemeConfig(
                    useSystemScheme = settings.useSystemScheme,
                    isNightMode = settings.isNightMode
                )
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainState.Loading
        )
}

sealed interface MainState {
    data object Loading : MainState
    data class Success(val value: ThemeConfig) : MainState
}

data class ThemeConfig(
    val useSystemScheme: Boolean,
    val isNightMode: Boolean
)