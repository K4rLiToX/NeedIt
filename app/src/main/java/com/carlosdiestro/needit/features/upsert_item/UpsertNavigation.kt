package com.carlosdiestro.needit.features.upsert_item

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val argCategory = "category"
const val argWishId = "wish_id"
val upsertArgs = listOf(
    navArgument(argCategory) { type = NavType.IntType },
    navArgument(argWishId) { type = NavType.LongType }
)
const val upsertBaseRoute = "upsert"

val upsertRoute = upsertArgs.fold(upsertBaseRoute) { acc, arg ->
    "$acc/{${arg.name}}"
}

fun NavController.navigateToUpsert(
    category: Int,
    wishId: Long = -1L
) {
    navigate(
        "$upsertBaseRoute/$category/$wishId"
    )
}

fun NavGraphBuilder.upsertScreen(
    onBackClick: () -> Unit,
    onFinish: () -> Unit
) {
    composable(
        route = upsertRoute,
        arguments = upsertArgs
    ) {
        UpsertRoute(
            onBackClick = onBackClick,
            onFinish = onFinish
        )
    }
}

