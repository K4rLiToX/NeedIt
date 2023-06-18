package com.carlosdiestro.needit.core.design_system.components.extensions

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.gradient(color: Color, endY: Float = 0.0F): Modifier = this.background(
    Brush.verticalGradient(
        listOf(color, Color.Transparent),
        startY = Float.POSITIVE_INFINITY,
        endY = endY
    )
)