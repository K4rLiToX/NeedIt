package com.carlosdiestro.needit.core.ui.design_system.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp

object Dimensions {
    object Button {
        val minHeight = 40.dp
        val largeHeight = 56.dp
        val outlinedButtonStrokeWidth = 1.dp
    }

    object IconButton {
        val small = 32.dp
        val medium = 40.dp
        val large = 48.dp
        val extraLarge = 56.dp
    }

    object Icon {
        val extraExtraSmall = 16.dp
        val extraSmall = 20.dp
        val small = 24.dp
        val medium = 40.dp
        val large = 48.dp
        val extraLarge = 56.dp
        val extraExtraLarge = 80.dp
    }

    object TabBar {
        val tabHeight = 48.dp
    }
}

val MaterialTheme.dimensions: Dimensions
    get() = Dimensions

val Dimensions.button: Dimensions.Button
    get() = Dimensions.Button
val Dimensions.iconButton: Dimensions.IconButton
    get() = Dimensions.IconButton

val Dimensions.icon: Dimensions.Icon
    get() = Dimensions.Icon

val Dimensions.tabBar: Dimensions.TabBar
    get() = Dimensions.TabBar
