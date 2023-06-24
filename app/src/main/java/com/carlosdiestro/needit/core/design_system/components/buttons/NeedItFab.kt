package com.carlosdiestro.needit.core.design_system.components.buttons

import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun NeedItFabVector(
    icon: ImageVector?,
    contentDescription: String = "",
    onClick: () -> Unit
) {
    if (icon != null) {
        LargeFloatingActionButton(
            onClick = onClick,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription
            )
        }
    }
}

@Composable
fun NeedItFabPainter(
    icon: Painter?,
    contentDescription: String = "",
    onClick: () -> Unit
) {
    if (icon != null) {
        LargeFloatingActionButton(
            onClick = onClick,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Icon(
                painter = icon,
                contentDescription = contentDescription
            )
        }
    }
}