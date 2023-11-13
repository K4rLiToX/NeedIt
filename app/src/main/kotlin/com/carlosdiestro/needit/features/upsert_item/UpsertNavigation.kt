package com.carlosdiestro.needit.features.upsert_item

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val argCategory = "category"
const val argWishId = "wish_id"
val upsertArgs = listOf(
    navArgument(argCategory) { type = NavType.IntType },
    navArgument(argWishId) { type = NavType.StringType }
)
const val upsertBaseRoute = "upsert"

val upsertRoute = upsertArgs.fold(upsertBaseRoute) { acc, arg ->
    "$acc/{${arg.name}}"
}

fun NavController.navigateToUpsert(
    category: Int,
    wishId: String = ""
) {
    navigate(
        "$upsertBaseRoute/$category/$wishId"
    )
}

fun NavGraphBuilder.upsertScreen(
    onBackClick: () -> Unit,
    onFinish: () -> Unit,
    enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition,
    popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition
) {
    composable(
        route = upsertRoute,
        arguments = upsertArgs,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    ) {
        UpsertRoute(
            onBackClick = onBackClick,
            onFinish = onFinish
        )
    }
}

