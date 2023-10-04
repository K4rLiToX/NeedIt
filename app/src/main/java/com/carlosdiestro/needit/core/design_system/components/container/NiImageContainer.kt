package com.carlosdiestro.needit.core.design_system.components.container

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun NiImageContainer(
    imageUrl: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "",
            modifier = Modifier.matchParentSize()
        )
        content()
    }
}