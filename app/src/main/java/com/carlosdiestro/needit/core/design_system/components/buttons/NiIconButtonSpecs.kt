package com.carlosdiestro.needit.core.design_system.components.buttons

import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Immutable
object NiIconButtonSpecs {
    object Size {
        val Default = 40.dp
        val Small = 32.dp
    }

    object Color {
        @Composable
        fun primary(): IconButtonColors {
            return IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        }

        @Composable
        fun secondary(): IconButtonColors {
            return IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        }

        @Composable
        fun neutral(): IconButtonColors {
            return IconButtonDefaults.filledIconButtonColors(
                containerColor = Color(0x80000000),
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        }

        @Composable
        fun transparent(): IconButtonColors {
            return IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}