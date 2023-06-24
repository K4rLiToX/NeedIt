package com.carlosdiestro.needit.features.gifts

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val giftsRoute = "gifts"

fun NavController.navigateToGifts() = navigate(giftsRoute)

fun NavGraphBuilder.giftsScreen() {
    composable(route = giftsRoute) {
        GiftsRoute()
    }
}