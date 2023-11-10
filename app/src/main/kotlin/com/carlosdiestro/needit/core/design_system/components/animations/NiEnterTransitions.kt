package com.carlosdiestro.needit.core.design_system.components.animations

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.ui.graphics.TransformOrigin

val topLevelEnterTransition: EnterTransition =
    fadeIn(
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    ) + scaleIn(
        animationSpec = tween(
            durationMillis = 100,
            easing = FastOutSlowInEasing
        )
    )

val forwardFromEndTransition: EnterTransition =
    slideInHorizontally(
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        initialOffsetX = { it * 2 }
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    )

val forwardFromStartTransition: EnterTransition =
    slideInHorizontally(
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        initialOffsetX = { -it * 2 }
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    )

val scaleInTransition: EnterTransition =
    scaleIn(
        animationSpec = tween(
            durationMillis = 100,
            easing = FastOutSlowInEasing
        )
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    )

val noEnterTransition: EnterTransition = EnterTransition.None
