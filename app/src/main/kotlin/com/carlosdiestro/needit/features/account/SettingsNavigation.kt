package com.carlosdiestro.needit.features.account

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val settingsRoute = "settings"

fun NavController.navigateToSettings() = navigate(settingsRoute)

fun NavGraphBuilder.settingsScreen(
    onBackClick: () -> Unit,
    onVersionClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    onTermsOfUseClick: () -> Unit
) {
    composable(
        route = settingsRoute
    ) {
        SettingsRoute(
            onBackClick = onBackClick,
            onVersionClick = onVersionClick,
            onPrivatePolicyClick = onPrivacyPolicyClick,
            onTermsOfUseClick = onTermsOfUseClick
        )
    }
}