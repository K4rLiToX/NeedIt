package com.carlosdiestro.needit.features.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.domain.preferences.GetSettingsUseCase
import com.carlosdiestro.needit.domain.preferences.UpdateIsNightModeUseCase
import com.carlosdiestro.needit.domain.preferences.UpdateUseSystemSchemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    getSettings: GetSettingsUseCase,
    private val updateUseSystemSchemeUseCase: UpdateUseSystemSchemeUseCase,
    private val updateIsNightModeUseCase: UpdateIsNightModeUseCase
) : ViewModel() {

    val state: StateFlow<SettingsDataState> = getSettings()
        .map { settings ->
            SettingsDataState(
                useSystemScheme = settings.useSystemScheme,
                isNightMode = settings.isNightMode
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SettingsDataState()
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