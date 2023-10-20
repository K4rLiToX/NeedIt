package com.carlosdiestro.needit.features.account

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val accountRoute = "account"

fun NavController.navigateToAccount(
    navOptions: NavOptions? = null
) = navigate(accountRoute, navOptions)

fun NavGraphBuilder.accountScreen(
    onBackClick: () -> Unit
) {
    composable(route = accountRoute) {
        AccountRoute(
            onBackClick = onBackClick
        )
    }
}