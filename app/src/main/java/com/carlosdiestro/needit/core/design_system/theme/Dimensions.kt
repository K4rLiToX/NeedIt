package com.carlosdiestro.needit.core.design_system.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimensions(
    val spacingXXS: Dp = 4.dp,
    val spacingXS: Dp = 8.dp,
    val spacingS: Dp = 12.dp,
    val spacingM: Dp = 16.dp,
    val spacingL: Dp = 24.dp,
    val spacingXL: Dp = 32.dp,
    val spacingXXL: Dp = 40.dp
)

val MaterialTheme.dimensions: Dimensions
    get() = Dimensions()