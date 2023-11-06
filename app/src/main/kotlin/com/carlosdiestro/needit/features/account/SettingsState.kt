package com.carlosdiestro.needit.features.account

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

internal data class SettingsDataState(
    val useSystemScheme: Boolean = true,
    val isNightMode: Boolean = false
) {
    val isNightModeEnabled: Boolean
        get() = !useSystemScheme
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
    val topAppBarState: TopAppBarState,
    val lazyListState: LazyListState
) {
    val scrollBehavior: TopAppBarScrollBehavior
        @Composable get() = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(topAppBarState)
}