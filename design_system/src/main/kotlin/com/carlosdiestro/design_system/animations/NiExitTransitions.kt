package com.carlosdiestro.design_system.animations

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.Alignment

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

val exitXSharedAxis: ExitTransition =
    slideOutHorizontally(
        animationSpec = tween(),
        targetOffsetX = {
            -30
        }
    ) + fadeOut(
        animationSpec = tween(
            durationMillis = 100
        )
    )

val popExitZSharedAxis: ExitTransition =
    fadeOut(
        animationSpec = tween(
            durationMillis = 100
        )
    ) + scaleOut(
        targetScale = 0.9F
    )

val popExitXSharedAxis: ExitTransition =
    slideOutHorizontally(
        animationSpec = tween(),
        targetOffsetX = {
            30
        }
    ) + fadeOut(
        animationSpec = tween(
            durationMillis = 100
        )
    )

val slideDown: ExitTransition =
    slideOutVertically(
        animationSpec = tween(),
        targetOffsetY = {
            it * 2
        }
    ) + shrinkVertically(
        animationSpec = tween(),
        shrinkTowards = Alignment.Bottom
    )

val exit: ExitTransition =
    fadeOut(
        animationSpec = tween(
            durationMillis = 80
        )
    )

val exitSlideUp: ExitTransition =
    slideOutVertically(
        animationSpec = tween(
            durationMillis = 100
        ),
        targetOffsetY = { -it * 2 }
    ) + shrinkVertically(
        animationSpec = tween(
            durationMillis = 100
        ),
        shrinkTowards = Alignment.Top
    )

val exitSlideDown: ExitTransition =
    slideOutVertically(
        animationSpec = tween(
            durationMillis = 100
        ),
        targetOffsetY = { it * 2 }
    ) + shrinkVertically(
        animationSpec = tween(
            durationMillis = 100
        ),
        shrinkTowards = Alignment.Bottom
    )