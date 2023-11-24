package com.carlosdiestro.design_system.buttons

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
internal fun NiButtonContent(
    label: String,
    leadIcon: ImageVector?,
    trailIcon: ImageVector?
) {
    if (leadIcon != null) {
        Icon(
            imageVector = leadIcon,
            contentDescription = "",
            modifier = Modifier
                .size(NiButtonSpecs.IconSize)
        )
        Spacer(modifier = Modifier.width(NiButtonSpecs.IconSpacing))
    }
    Text(text = label)
    if (trailIcon != null) {
        Spacer(modifier = Modifier.width(NiButtonSpecs.IconSpacing))
        Icon(
            imageVector = trailIcon,
            contentDescription = "",
            modifier = Modifier
                .size(NiButtonSpecs.IconSize)
        )
    }
}