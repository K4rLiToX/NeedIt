package com.carlosdiestro.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.app_settings.domain.ThemeConfig
import com.carlosdiestro.app_settings.usecases.GetThemeConfigUseCase
import com.carlosdiestro.app_settings.usecases.UpdateThemeConfigUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SettingsViewModel @Inject constructor(
    getThemeConfig: GetThemeConfigUseCase,
    private val updateThemeConfig: UpdateThemeConfigUseCase
) : ViewModel() {

    val state: StateFlow<SettingsState> = getThemeConfig()
        .map { themeConfig ->
            SettingsState.Success(
                selectedTheme = themeConfig.asPlo()
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SettingsState.Loading
        )

    fun updateThemeConfig(themeConfig: ThemeConfigPlo) {
        viewModelScope.launch {
            updateThemeConfig(themeConfig.asDomain())
        }
    }

    fun updateFriendRequests() {

    }

    fun updateAdditionToGroups() {

    }
}

fun ThemeConfig.asPlo(): ThemeConfigPlo = ThemeConfigPlo.entries[this.ordinal]

fun ThemeConfigPlo.asDomain(): ThemeConfig = ThemeConfig.entries[this.ordinal]