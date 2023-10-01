package com.carlosdiestro.needit.core.design_system.components.buttons

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.design_system.theme.icons

@Composable
fun NiIconButton(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    buttonSize: Dp = NiIconButtonSpecs.Size.Default,
    colors: IconButtonColors = NiIconButtonSpecs.Color.primary(),
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        colors = colors,
        modifier = modifier
            .clip(CircleShape)
            .size(buttonSize)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun NiIconButtonsPreview() {
    NeedItTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NiIconButton()
            NiSmallIconButton()
        }
    }
}

@Composable
internal fun NiIconButton() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NiIconButton(
            icon = MaterialTheme.icons.AddFriend,
            onClick = {}
        )
        NiIconButton(
            icon = MaterialTheme.icons.AddFriend,
            colors = NiIconButtonSpecs.Color.secondary(),
            onClick = {}
        )
        NiIconButton(
            icon = MaterialTheme.icons.AddFriend,
            colors = NiIconButtonSpecs.Color.neutral(),
            onClick = {}
        )
        NiIconButton(
            icon = MaterialTheme.icons.AddFriend,
            colors = NiIconButtonSpecs.Color.transparent(),
            onClick = {}
        )
    }
}

@Composable
internal fun NiSmallIconButton() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NiIconButton(
            icon = MaterialTheme.icons.AddFriend,
            buttonSize = NiIconButtonSpecs.Size.Small,
            onClick = {}
        )
        NiIconButton(
            icon = MaterialTheme.icons.AddFriend,
            buttonSize = NiIconButtonSpecs.Size.Small,
            colors = NiIconButtonSpecs.Color.secondary(),
            onClick = {}
        )
        NiIconButton(
            icon = MaterialTheme.icons.AddFriend,
            buttonSize = NiIconButtonSpecs.Size.Small,
            colors = NiIconButtonSpecs.Color.neutral(),
            onClick = {}
        )
        NiIconButton(
            icon = MaterialTheme.icons.AddFriend,
            buttonSize = NiIconButtonSpecs.Size.Small,
            colors = NiIconButtonSpecs.Color.transparent(),
            onClick = {}
        )
    }
}