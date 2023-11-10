package com.carlosdiestro.needit.core.design_system.components.animations

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut

val exitNone: ExitTransition = ExitTransition.None

val exitFadeThrough: ExitTransition =
    fadeOut(
        animationSpec = tween(
            durationMillis = 100
        )
    )

val exitZSharedAxis: ExitTransition =
    fadeOut(
        animationSpec = tween(
            durationMillis = 100
        )
    ) + scaleOut(
        targetScale = 1.1F
    )

val popExitZSharedAxis: ExitTransition =
    fadeOut(
        animationSpec = tween(
            durationMillis = 100
        )
    ) + scaleOut(
        targetScale = 0.9F
    )

val exit: ExitTransition =
    fadeOut(
        animationSpec = tween(
            durationMillis = 80
        )
    )