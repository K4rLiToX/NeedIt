package com.carlosdiestro.needit.features.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.domain.settings.GetSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    getSettings: GetSettingsUseCase
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

    }

    fun updateNightMode() {

    }

    fun updateFriendRequests() {

    }

    fun updateAdditionToGroups() {

    }
}