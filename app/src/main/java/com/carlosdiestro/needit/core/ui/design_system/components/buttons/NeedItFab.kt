package com.carlosdiestro.needit.core.ui.design_system.components.buttons

import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun NeedItFab(
    icon: ImageVector,
    contentDescription: String = "",
    onClick: () -> Unit
) {
    LargeFloatingActionButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription
        )
    }
}