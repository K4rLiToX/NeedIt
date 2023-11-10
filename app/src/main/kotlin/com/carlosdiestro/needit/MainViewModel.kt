package com.carlosdiestro.needit

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.auth.AuthClient
import com.carlosdiestro.needit.core.mappers.asPlo
import com.carlosdiestro.needit.domain.preferences.usecases.GetThemeConfigUseCase
import com.carlosdiestro.needit.domain.users.usecases.GetSignedInUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getThemeConfig: GetThemeConfigUseCase,
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

    val state: StateFlow<MainState> = getThemeConfig()
        .map { themeConfig ->
            MainState.Success(
                value = themeConfig.asPlo()
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainState.Loading
        )
}

sealed interface MainState {
    data object Loading : MainState
    data class Success(val value: ThemeConfigPlo) : MainState
}

enum class ThemeConfigPlo(@StringRes val labelId: Int) {
    FollowSystem(R.string.settings_display_section_theme_dialog_use_system),
    Light(R.string.settings_display_section_theme_dialog_light),
    Dark(R.string.settings_display_section_theme_dialog_dark)
}