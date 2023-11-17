package com.carlosdiestro.needit.core.design_system.components.avatars

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.design_system.theme.icons
import com.carlosdiestro.needit.core.design_system.theme.shape

@Composable
fun NiAvatar(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    size: Dp = NiAvatarSpecs.Large,
    onClick: (() -> Unit)? = null
) {
    val imageVector = rememberVectorPainter(image = MaterialTheme.icons.Profile)
    AsyncImage(
        model = imageUrl?.ifEmpty { null },
        contentDescription = "",
        contentScale = ContentScale.Crop,
        fallback = imageVector,
        modifier = modifier
            .clip(MaterialTheme.shape.full)
            .size(size)
            .clickable {
                if (onClick != null) {
                    onClick()
                }
            }
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun NiAvatarPreview() {
    NeedItTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NiImageAvatar()
            NiIconAvatar()
        }
    }
}

@Composable
internal fun NiImageAvatar() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NiAvatar(
            imageUrl = "https://lh3.googleusercontent" +
                    ".com/a/ACg8ocIAt4DgDPUFisU08VoLLXtS06lloC8ftW_GpGN1AkekwWY=s96-c",
            size = NiAvatarSpecs.ExtraSmall
        )
        NiAvatar(
            imageUrl = "https://lh3.googleusercontent" +
                    ".com/a/ACg8ocIAt4DgDPUFisU08VoLLXtS06lloC8ftW_GpGN1AkekwWY=s96-c",
            size = NiAvatarSpecs.Small
        )
        NiAvatar(
            imageUrl = "https://lh3.googleusercontent" +
                    ".com/a/ACg8ocIAt4DgDPUFisU08VoLLXtS06lloC8ftW_GpGN1AkekwWY=s96-c",
            size = NiAvatarSpecs.Medium
        )
        NiAvatar(
            imageUrl = "https://lh3.googleusercontent" +
                    ".com/a/ACg8ocIAt4DgDPUFisU08VoLLXtS06lloC8ftW_GpGN1AkekwWY=s96-c",
            size = NiAvatarSpecs.Large
        )
        NiAvatar(
            imageUrl = "https://lh3.googleusercontent" +
                    ".com/a/ACg8ocIAt4DgDPUFisU08VoLLXtS06lloC8ftW_GpGN1AkekwWY=s96-c",
            size = NiAvatarSpecs.ExtraLarge
        )
    }
}

@Composable
internal fun NiIconAvatar() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NiAvatar(
            size = NiAvatarSpecs.ExtraSmall
        )
        NiAvatar(
            size = NiAvatarSpecs.Small
        )
        NiAvatar(
            size = NiAvatarSpecs.Medium
        )
        NiAvatar(
            size = NiAvatarSpecs.Large
        )
        NiAvatar(
            size = NiAvatarSpecs.ExtraLarge
        )
    }
}