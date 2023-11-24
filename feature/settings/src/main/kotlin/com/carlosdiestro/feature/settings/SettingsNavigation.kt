package com.carlosdiestro.feature.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val SETTINGS_BASE_ROUTE = "settings"

object SettingsDestination {

    const val route = SETTINGS_BASE_ROUTE

    fun getDestination(): String {
        return SETTINGS_BASE_ROUTE
    }
}

fun NavController.navigateToSettings() = navigate(SettingsDestination.getDestination())

fun NavGraphBuilder.settingsScreen(
    onBackClick: () -> Unit,
    onVersionClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    onTermsOfUseClick: () -> Unit
) {
    composable(
        route = SettingsDestination.route
    ) {
        SettingsRoute(
            onBackClick = onBackClick,
            onVersionClick = onVersionClick,
            onPrivatePolicyClick = onPrivacyPolicyClick,
            onTermsOfUseClick = onTermsOfUseClick
        )
    }
}