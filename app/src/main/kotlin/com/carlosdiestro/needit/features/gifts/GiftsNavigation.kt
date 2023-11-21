package com.carlosdiestro.needit.features.gifts

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.carlosdiestro.design_system.navigation.destinations.topLevelDestination

private const val GIFTS_BASE_ROUTE = "gifts"

object GiftsDestination {

    const val route = GIFTS_BASE_ROUTE

    fun getDestination(): String {
        return GIFTS_BASE_ROUTE
    }
}

fun NavController.navigateToGifts(
    navOptions: NavOptions? = null
) = navigate(GiftsDestination.getDestination(), navOptions)

fun NavGraphBuilder.giftsScreen(
    enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition,
    popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition
) {
    topLevelDestination(
        route = GiftsDestination.route,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    ) {
        GiftsRoute()
    }
}