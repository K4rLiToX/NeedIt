package com.carlosdiestro.needit.features.upsert_item

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val argImageUrl = "image_url"
const val argCategory = "category"
const val argWishId = "wish_id"
val upsertArgs = listOf(
    navArgument(argImageUrl) { type = NavType.StringType },
    navArgument(argCategory) { type = NavType.IntType },
    navArgument(argWishId) { type = NavType.LongType }
)
const val upsertBaseRoute = "upsert"

val upsertRoute = upsertArgs.fold(upsertBaseRoute) { acc, arg ->
    "$acc/{${arg.name}}"
}

fun NavController.navigateToUpsert(
    imageUrl: String,
    category: Int,
    wishId: Long = -1L
) {
    navigate(
        "$upsertBaseRoute/{$imageUrl}/{$category}/{$wishId}"
    )
}

fun NavGraphBuilder.upsert(

) {
    composable(
        route = upsertRoute,
        arguments = upsertArgs
    ) {
        UpsertRoute()
    }
}

