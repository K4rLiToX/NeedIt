package com.carlosdiestro.needit.core.design_system.components.buttons

import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun NeedItFab(
    icon: ImageVector?,
    contentDescription: String = "",
    onClick: () -> Unit
) {
    if (icon != null) {
        LargeFloatingActionButton(
            onClick = onClick
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription
            )
        }
    }
}