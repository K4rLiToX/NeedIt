package com.carlosdiestro.needit.core.ui.design_system.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp

object Dimensions {
    object Button {
        val minHeight = 40.dp
        val largeHeight = 56.dp
        val outlinedButtonStrokeWidth = 1.dp
    }
}

val MaterialTheme.dimensions: Dimensions
    get() = Dimensions

val Dimensions.button: Dimensions.Button
    get() = Dimensions.Button