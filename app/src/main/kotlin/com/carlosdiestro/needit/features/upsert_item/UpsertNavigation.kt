package com.carlosdiestro.needit.features.upsert_item

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
import com.carlosdiestro.design_system.lists.WishCategoryPlo
import com.carlosdiestro.design_system.lists.toWishCategoryPlo

private const val UPSERT_BASE_ROUTE = "upsert"
private const val UPSERT_ARG_CATEGORY = "category"
private const val UPSERT_ARG_WISH_ID = "wish_id"

object UpsertDestination {

    const val route = "$UPSERT_BASE_ROUTE/{$UPSERT_ARG_CATEGORY}/{$UPSERT_ARG_WISH_ID}"
    val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(UPSERT_ARG_CATEGORY) {
                type = NavType.IntType
            },
            navArgument(UPSERT_ARG_WISH_ID) {
                type = NavType.StringType
            }
        )

    fun getDestination(
        category: Int,
        wishId: String
    ): String {
        return UPSERT_BASE_ROUTE +
                "/{$category}" +
                "/{$wishId}"
    }
}

internal class UpsertNavArgs private constructor(
    val category: WishCategoryPlo,
    val wishId: String
) {
    companion object {
        fun fromSavedState(savedStateHandle: SavedStateHandle): UpsertNavArgs =
            UpsertNavArgs(
                category = (savedStateHandle[UPSERT_ARG_CATEGORY] ?: -1).toWishCategoryPlo(),
                wishId = savedStateHandle[UPSERT_ARG_WISH_ID] ?: "none"
            )
    }
}

fun NavController.navigateToUpsert(
    category: Int,
    wishId: String
) {
    navigate(UpsertDestination.getDestination(category, wishId))
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
        route = UpsertDestination.route,
        arguments = UpsertDestination.arguments,
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

