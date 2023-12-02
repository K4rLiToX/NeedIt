package com.carlosdiestro.feature.notifications

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val NOTIFICATIONS_BASE_ROUTE = "notifications"

object NotificationsDestination {

    const val route = NOTIFICATIONS_BASE_ROUTE

    fun getDestination(): String {
        return NOTIFICATIONS_BASE_ROUTE
    }
}

fun NavController.navigateToNotifications() = navigate(NotificationsDestination.getDestination())

fun NavGraphBuilder.notificationsScreen(
    onBackClick: () -> Unit,
    enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition,
    popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition
) {
    composable(
        route = NotificationsDestination.route,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    ) {
        NotificationsRoute(
            onBackClick = onBackClick,
        )
    }
}