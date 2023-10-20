package com.carlosdiestro.needit.core.design_system.components.navigation.destinations

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.carlosdiestro.needit.core.design_system.components.animations.topLeveExitTransition
import com.carlosdiestro.needit.core.design_system.components.animations.topLevelEnterTransition

fun NavGraphBuilder.topLevelDestination(
    route: String,
    screen: @Composable() (AnimatedContentScope.(NavBackStackEntry) -> Unit)
) {
    composable(
        route = route,
        enterTransition = topLevelEnterTransition,
        exitTransition = topLeveExitTransition,
        content = screen
    )
}