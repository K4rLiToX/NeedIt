package com.carlosdiestro.needit.core.design_system.components.animations

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.navigation.NavBackStackEntry

val topLevelEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() ->
EnterTransition = {
    fadeIn(
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearEasing
        )
    )
}

val noEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() ->
EnterTransition = {
    fadeIn(
        animationSpec = tween(durationMillis = 300),
        initialAlpha = 0.99F
    )
}