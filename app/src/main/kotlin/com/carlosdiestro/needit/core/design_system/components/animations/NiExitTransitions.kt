package com.carlosdiestro.needit.core.design_system.components.animations

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.navigation.NavBackStackEntry

val topLeveExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() ->
ExitTransition = {
    fadeOut(
        animationSpec = tween(
            durationMillis = 100,
            easing = FastOutSlowInEasing
        )
    ) + scaleOut(
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    )
}

val noExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() ->
ExitTransition = {
    fadeOut(
        animationSpec = tween(durationMillis = 300),
        targetAlpha = 0.99F
    )
}