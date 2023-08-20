package com.carlosdiestro.needit.core.design_system.components.images

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import com.carlosdiestro.needit.core.design_system.components.buttons.IconButtonSpecs

@Composable
fun NeedItAvatar(
    imageUrl: String,
    contentDescription: String = "",
    size: Dp = IconButtonSpecs.iconMedium
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        modifier = Modifier
            .clip(CircleShape)
            .size(size)
    )
}