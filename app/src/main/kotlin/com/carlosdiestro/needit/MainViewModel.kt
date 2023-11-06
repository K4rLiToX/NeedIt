package com.carlosdiestro.needit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.auth.AuthClient
import com.carlosdiestro.needit.domain.preferences.usecases.GetSettingsUseCase
import com.carlosdiestro.needit.domain.users.usecases.GetSignedInUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getSignedInUser: GetSignedInUserUseCase,
    getSettings: GetSettingsUseCase,
    private val authClient: AuthClient
) : ViewModel() {

    val state: StateFlow<MainState> = combine(
        getSignedInUser(),
        getSettings()
    ) { user, settings ->
        MainState(
            profilePictureUrl = user.profilePictureUrl,
            isUserAnonymous = user.isAnonymous,
            isSignedIn = authClient.signedInUser != null,
            useSystemScheme = settings.useSystemScheme,
            isNightMode = settings.isNightMode
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MainState(isSignedIn = authClient.signedInUser != null)
    )
}

data class MainState(
    val profilePictureUrl: String = "",
    val isUserAnonymous: Boolean = true,
    val isSignedIn: Boolean,
    val useSystemScheme: Boolean = true,
    val isNightMode: Boolean = false
)