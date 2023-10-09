package com.carlosdiestro.needit.core.design_system.components.navigation.navigation_bar

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Immutable
object NiNavigationSpecs {
    @Immutable
    object Color {
        val SelectedIcon: androidx.compose.ui.graphics.Color
            @Composable get() = MaterialTheme.colorScheme.onPrimaryContainer
        val UnselectedIcon: androidx.compose.ui.graphics.Color
            @Composable get() = MaterialTheme.colorScheme.onSurfaceVariant
        val SelectedLabel: androidx.compose.ui.graphics.Color
            @Composable get() = MaterialTheme.colorScheme.onSurface
        val UnselectedLabel: androidx.compose.ui.graphics.Color
            @Composable get() = MaterialTheme.colorScheme.onSurfaceVariant
        val IndicatorColor: androidx.compose.ui.graphics.Color
            @Composable get() = MaterialTheme.colorScheme.primaryContainer
    }
}