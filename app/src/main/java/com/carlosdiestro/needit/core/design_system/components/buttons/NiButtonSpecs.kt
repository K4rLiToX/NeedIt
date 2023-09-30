package com.carlosdiestro.needit.core.design_system.components.buttons

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp

@Immutable
object NiButtonSpecs {
    val IconSpacing = ButtonDefaults.IconSpacing
    val IconSize = ButtonDefaults.IconSize

    object Height {
        val Default = ButtonDefaults.MinHeight
        val Large = 56.dp
    }

    object Color {
        @Composable
        fun primary(): ButtonColors {
            return ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        }

        @Composable
        fun secondary(): ButtonColors {
            return ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        }

        @Composable
        fun neutral(): ButtonColors {
            return ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        }

        @Composable
        fun error(): ButtonColors {
            return ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            )
        }
    }
}





