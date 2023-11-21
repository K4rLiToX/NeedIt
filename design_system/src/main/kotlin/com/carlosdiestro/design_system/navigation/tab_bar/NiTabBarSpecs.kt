package com.carlosdiestro.design_system.navigation.tab_bar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp
import com.carlosdiestro.design_system.theme.shape

@Immutable
object NiTabBarSpecs {
    val TabHeight = 48.dp
    val IndicatorColor: androidx.compose.ui.graphics.Color
        @Composable get() = MaterialTheme.colorScheme.primary
    val IndicatorShape: RoundedCornerShape
        @Composable get() = MaterialTheme.shape.topFull

    @Immutable
    object Color {
        val transparent: androidx.compose.ui.graphics.Color
            @Composable get() = androidx.compose.ui.graphics.Color.Transparent
        val secondary: androidx.compose.ui.graphics.Color
            @Composable get() = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
    }

    @Immutable
    object TabContentColor {
        val Selected: androidx.compose.ui.graphics.Color
            @Composable get() = MaterialTheme.colorScheme.primary
        val Unselected: androidx.compose.ui.graphics.Color
            @Composable get() = MaterialTheme.colorScheme.onSurfaceVariant
    }
}