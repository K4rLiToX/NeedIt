package com.carlosdiestro.needit.features.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.domain.preferences.usecases.GetSettingsUseCase
import com.carlosdiestro.needit.domain.preferences.usecases.UpdateIsNightModeUseCase
import com.carlosdiestro.needit.domain.preferences.usecases.UpdateUseSystemSchemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SettingsViewModel @Inject constructor(
    getSettings: GetSettingsUseCase,
    private val updateUseSystemSchemeUseCase: UpdateUseSystemSchemeUseCase,
    private val updateIsNightModeUseCase: UpdateIsNightModeUseCase
) : ViewModel() {

    val state: StateFlow<SettingsState> = getSettings()
        .map { settings ->
            SettingsState.Success(
                value = ThemeConfig(
                    useSystemScheme = settings.useSystemScheme,
                    isNightMode = settings.isNightMode
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SettingsState.Loading
        )

    fun updateUseSystemScheme() {
        viewModelScope.launch {
            updateUseSystemSchemeUseCase()
        }
    }

    fun updateNightMode() {
        viewModelScope.launch {
            updateIsNightModeUseCase()
        }
    }

    fun updateFriendRequests() {

    }

    fun updateAdditionToGroups() {

    }
}