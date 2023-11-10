package com.carlosdiestro.needit.core.design_system.components.animations

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideOutHorizontally

val topLevelExitTransition: ExitTransition =
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

val backwardToEndTransition: ExitTransition =
    slideOutHorizontally(
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        targetOffsetX = { it * 2 }
    ) + fadeOut(
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    )

val backwardToStartTransition: ExitTransition =
    slideOutHorizontally(
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        targetOffsetX = { -it * 2 }
    ) + fadeOut(
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    )

val scaleOutTransition: ExitTransition =
    scaleOut(
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    ) + fadeOut(
        animationSpec = tween(
            durationMillis = 100,
            easing = FastOutSlowInEasing
        )
    )

val noExitTransition: ExitTransition = ExitTransition.None