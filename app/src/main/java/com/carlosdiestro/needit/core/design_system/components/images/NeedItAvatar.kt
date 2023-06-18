package com.carlosdiestro.needit.core.design_system.components.images

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icon

@Composable
fun NeedItAvatar(
    imageUrl: String,
    contentDescription: String = "",
    size: Dp = MaterialTheme.dimensions.icon.medium
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
    )
}