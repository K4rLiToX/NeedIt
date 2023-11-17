package com.carlosdiestro.design_system.components.buttons

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource

@Composable
internal fun NiButtonContent(
    @StringRes labelId: Int,
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
    Text(text = stringResource(id = labelId))
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