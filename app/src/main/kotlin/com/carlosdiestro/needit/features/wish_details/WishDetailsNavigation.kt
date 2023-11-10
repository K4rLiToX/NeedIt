package com.carlosdiestro.needit.features.wish_details

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.carlosdiestro.needit.core.design_system.components.animations.backwardToEndTransition
import com.carlosdiestro.needit.core.design_system.components.animations.forwardFromEndTransition

const val argsWishId = "wish_id"
val detailsArgs = listOf(
    navArgument(argsWishId) { type = NavType.LongType }
)

const val detailsBaseRoute = "wish_details"

val detailsRoute = detailsArgs.fold(detailsBaseRoute) { acc, arg ->
    "$acc/{${arg.name}}"
}

fun NavController.navigateToWishDetails(
    wishId: Long
) {
    navigate(
        "$detailsBaseRoute/$wishId"
    )
}

fun NavGraphBuilder.wishDetailsScreen(
    onBackClick: () -> Unit,
    onUpdateClick: (Int, Long) -> Unit,
    enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition,
    popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition
) {
    composable(
        route = detailsRoute,
        arguments = detailsArgs,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    ) {
        WishDetailsRoute(
            onBackClick = onBackClick,
            onUpdateClick = onUpdateClick
        )
    }
}