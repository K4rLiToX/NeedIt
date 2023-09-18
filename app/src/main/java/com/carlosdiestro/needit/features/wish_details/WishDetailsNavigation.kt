package com.carlosdiestro.needit.features.wish_details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

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

fun NavGraphBuilder.wishDetailsRoute(
    onBackClick: () -> Unit,
    onUpdateClick: (String, Int, Long) -> Unit
) {
    composable(
        route = detailsRoute,
        arguments = detailsArgs
    ) {
        WishDetailsRoute(
            onBackClick = onBackClick,
            onUpdateClick = onUpdateClick
        )
    }
}