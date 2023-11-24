package com.carlosdiestro.feature.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

internal sealed interface SettingsState {
    data object Loading : SettingsState
    data class Success(val selectedTheme: ThemeConfigPlo) : SettingsState
}

enum class ThemeConfigPlo(@StringRes val labelId: Int) {
    FollowSystem(R.string.settings_display_section_theme_dialog_use_system),
    Light(R.string.settings_display_section_theme_dialog_light),
    Dark(R.string.settings_display_section_theme_dialog_dark)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun rememberSettingsUiState(
    topAppBarState: TopAppBarState = rememberTopAppBarState(),
    lazyListState: LazyListState = rememberLazyListState()
): SettingsUiState {
    return remember(
        topAppBarState,
        lazyListState
    ) {
        SettingsUiState(
            topAppBarState = topAppBarState,
            lazyListState = lazyListState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Stable
internal class SettingsUiState constructor(
    val lazyListState: LazyListState,
    private val topAppBarState: TopAppBarState
) {
    val scrollBehavior: TopAppBarScrollBehavior
        @Composable get() = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(topAppBarState)

    var shouldShowThemeDialog by mutableStateOf(false)
        private set

    fun openThemeDialog() {
        shouldShowThemeDialog = true
    }

    fun closeThemeDialog() {
        shouldShowThemeDialog = false
    }
}