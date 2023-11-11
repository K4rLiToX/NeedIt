package com.carlosdiestro.needit.core.design_system.components.animations

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.ui.Alignment

val enterNone: EnterTransition = EnterTransition.None

val enterFadeThrough: EnterTransition =
    fadeIn(
        animationSpec = tween(
            durationMillis = 200,
            delayMillis = 100
        )
    ) + scaleIn(
        animationSpec = tween(
            durationMillis = 200,
            delayMillis = 100
        ),
        initialScale = 0.92F
    )

val enterZSharedAxis: EnterTransition =
    scaleIn(
        initialScale = 0.8F
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = 200,
            delayMillis = 100
        )
    )

val enterXSharedAxis: EnterTransition =
    slideInHorizontally(
        animationSpec = tween(),
        initialOffsetX = {
            30
        }
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = 200,
            delayMillis = 100
        )
    )

val popEnterZSharedAxis: EnterTransition =
    scaleIn(
        initialScale = 1.2F
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = 200,
            delayMillis = 100
        )
    )

val popEnterXSharedAxis: EnterTransition =
    slideInHorizontally(
        animationSpec = tween(),
        initialOffsetX = {
            -30
        }
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = 200,
            delayMillis = 100
        )
    )

val enterSlideUp: EnterTransition =
    slideInVertically(
        animationSpec = tween(),
        initialOffsetY = { it * 2 }
    ) + expandVertically(
        animationSpec = tween(),
        expandFrom = Alignment.Bottom
    )

val enter: EnterTransition =
    fadeIn(
        animationSpec = tween(
            durationMillis = 60
        )
    ) + scaleIn(
        animationSpec = tween(
            durationMillis = 150
        ),
        initialScale = 0.8F
    )