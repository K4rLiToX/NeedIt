package com.carlosdiestro.needit.features.camera

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val CAMERA_BASE_ROUTE = "camera"

object CameraDestination {

    const val route = CAMERA_BASE_ROUTE

    fun getDestination(): String {
        return CAMERA_BASE_ROUTE
    }
}

fun NavController.navigateToCamera() = navigate(CameraDestination.getDestination())

fun NavGraphBuilder.cameraScreen(
    onBackClick: () -> Unit,
    onContinueClick: (Int, String) -> Unit,
    enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition,
    popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition
) {
    composable(
        route = CameraDestination.route,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    ) {
        CameraRoute(
            onBackClick = onBackClick,
            onContinueClick = onContinueClick
        )
    }
}