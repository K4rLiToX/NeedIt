package com.carlosdiestro.feature.wish_details

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val WISH_DETAILS_BASE_ROUTE = "wish_details"
private const val WISH_DETAILS_ARG_WISH_ID = "wish_id"

object WishDetailsDestination {

    const val route = "$WISH_DETAILS_BASE_ROUTE/{$WISH_DETAILS_ARG_WISH_ID}"
    val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(WISH_DETAILS_ARG_WISH_ID) {
                type = NavType.StringType
            }
        )

    fun getDestination(
        wishId: String
    ): String {
        return WISH_DETAILS_BASE_ROUTE +
                "/$wishId"
    }
}

internal class WishDetailsNavArgs private constructor(
    val wishId: String
) {
    companion object {
        fun fromSavedState(savedStateHandle: SavedStateHandle): WishDetailsNavArgs =
            WishDetailsNavArgs(
                wishId = savedStateHandle[WISH_DETAILS_ARG_WISH_ID] ?: "none"
            )
    }
}

fun NavController.navigateToWishDetails(
    wishId: String
) {
    navigate(WishDetailsDestination.getDestination(wishId))
}

fun NavGraphBuilder.wishDetailsScreen(
    onBackClick: () -> Unit,
    onUpdateClick: (Int, String) -> Unit,
    enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition,
    popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition
) {
    composable(
        route = WishDetailsDestination.route,
        arguments = WishDetailsDestination.arguments,
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