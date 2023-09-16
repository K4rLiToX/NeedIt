package com.carlosdiestro.needit.core.design_system.components.images

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.carlosdiestro.needit.core.design_system.components.extensions.shimmerEffect

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NeedItImageContainer(
    imageUrl: String,
    contentDescription: String = "",
    width: Dp = Dp.Unspecified,
    height: Dp = Dp.Unspecified,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {},
    cardContent: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = modifier
            .size(width = width, height = height)
            .clip(RoundedCornerShape(24.dp))
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .size(Size.ORIGINAL)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop
        )
        val imageState = painter.state
        if (imageState is AsyncImagePainter.State.Loading) {
            ImageLoading()
        } else if (imageState is AsyncImagePainter.State.Success) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            cardContent()
        }
    }
}

@Composable
private fun ImageLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .shimmerEffect()
    )
}