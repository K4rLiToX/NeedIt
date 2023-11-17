package com.carlosdiestro.design_system.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Shapes(
    private val m: Dp = 24.dp,
    private val l: Dp = 32.dp
) {
    val none: Shape
        get() = RectangleShape

    val medium: RoundedCornerShape
        get() = RoundedCornerShape(this.m)

    val large: RoundedCornerShape
        get() = RoundedCornerShape(this.l)

    val topMedium: RoundedCornerShape
        get() = RoundedCornerShape(
            topStart = this.m,
            topEnd = this.m
        )

    val bottomMedium: RoundedCornerShape
        get() = RoundedCornerShape(
            bottomStart = this.m,
            bottomEnd = this.m
        )

    val full: Shape
        get() = CircleShape

    val topFull: RoundedCornerShape
        get() = RoundedCornerShape(
            topStartPercent = 100,
            topEndPercent = 100
        )
}

internal val LocalShapes = staticCompositionLocalOf {
    Shapes()
}

val MaterialTheme.shape: Shapes
    @Composable get() = LocalShapes.current