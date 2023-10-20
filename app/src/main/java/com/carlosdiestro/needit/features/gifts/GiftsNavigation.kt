package com.carlosdiestro.needit.features.gifts

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.carlosdiestro.needit.core.design_system.components.navigation.destinations.topLevelDestination

const val giftsRoute = "gifts"

fun NavController.navigateToGifts() = navigate(giftsRoute)

fun NavGraphBuilder.giftsScreen() {
    topLevelDestination(
        route = giftsRoute
    ) {
        GiftsRoute()
    }
}