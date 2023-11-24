package com.carlosdiestro.feature.home

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.carlosdiestro.design_system.navigation.destinations.topLevelDestination

private const val HOME_BASE_ROUTE = "home"

object HomeDestination {

    const val route = HOME_BASE_ROUTE

    fun getDestination(): String {
        return HOME_BASE_ROUTE
    }
}

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) = navigate(HomeDestination.getDestination(), navOptions)

fun NavGraphBuilder.homeScreen(
    onItemClick: (String) -> Unit,
    onUpdateClick: (Int, String) -> Unit,
    onCreateClick: () -> Unit,
    enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition,
    popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition

) {
    topLevelDestination(
        route = HomeDestination.route,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    ) {
        HomeRoute(
            onItemClick = onItemClick,
            onUpdateClick = onUpdateClick,
            onCreateClick = onCreateClick
        )
    }
}