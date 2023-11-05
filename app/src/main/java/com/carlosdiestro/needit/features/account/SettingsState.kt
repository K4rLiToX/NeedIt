package com.carlosdiestro.needit.features.account

data class SettingsDataState(
    val useSystemScheme: Boolean = true,
    val isNightMode: Boolean = false
) {
    val isNightModeEnabled: Boolean
        get() = !useSystemScheme
}