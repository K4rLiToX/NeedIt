package com.carlosdiestro.needit.features.home

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.carlosdiestro.needit.core.design_system.components.navigation.destinations.topLevelDestination
import com.carlosdiestro.needit.features.sign_in.signInRoute

const val homeRoute = "home"

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) = navigate(homeRoute, navOptions)

fun NavController.navigateToHomeCleaningBackStack() = navigate(homeRoute) {
    popUpTo(signInRoute) {
        inclusive = true
    }
}

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
        route = homeRoute,
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