package com.carlosdiestro.needit.core.design_system.components.buttons

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.design_system.theme.button
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icon
import com.carlosdiestro.needit.core.design_system.theme.iconButton
import com.carlosdiestro.needit.core.design_system.theme.icons
import com.carlosdiestro.needit.core.design_system.theme.spacing

private enum class IconButtonStyle {
    Filled,
    Outlined,
    Labeled
}

@Composable
private fun NeedItBaseIconButton(
    @StringRes labelId: Int = -1,
    icon: ImageVector,
    contentDescription: String = "",
    containerColor: Color = MaterialTheme.colorScheme.background,
    containerSize: Dp = MaterialTheme.dimensions.iconButton.large,
    iconTint: Color = MaterialTheme.colorScheme.onBackground,
    iconSize: Dp = MaterialTheme.dimensions.icon.small,
    style: IconButtonStyle = IconButtonStyle.Filled,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    when (style) {
        IconButtonStyle.Filled -> {
            IconButton(
                onClick = onClick,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = containerColor,
                    contentColor = iconTint
                ),
                modifier = modifier
                    .clip(CircleShape)
                    .size(containerSize)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .size(iconSize)
                )
            }
        }

        IconButtonStyle.Outlined -> {
            OutlinedIconButton(
                onClick = onClick,
                border = BorderStroke(
                    width = MaterialTheme.dimensions.button.outlinedButtonStrokeWidth,
                    color = MaterialTheme.colorScheme.primaryContainer
                ),
                modifier = modifier
                    .size(MaterialTheme.dimensions.icon.extraLarge)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        IconButtonStyle.Labeled -> {
            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xs),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NeedItFilledIconButton(
                    icon = icon,
                    contentDescription = contentDescription,
                    modifier = modifier,
                    onClick = onClick
                )
                Text(
                    text = context.getString(labelId),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun NeedItFilledIconButton(
    icon: ImageVector,
    contentDescription: String = "",
    containerColor: Color = MaterialTheme.colorScheme.background,
    containerSize: Dp = MaterialTheme.dimensions.iconButton.large,
    iconTint: Color = MaterialTheme.colorScheme.onBackground,
    iconSize: Dp = MaterialTheme.dimensions.icon.small,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    NeedItBaseIconButton(
        icon = icon,
        contentDescription = contentDescription,
        containerColor = containerColor,
        containerSize = containerSize,
        iconTint = iconTint,
        iconSize = iconSize,
        modifier = modifier,
        onClick = onClick
    )
}

@Composable
fun NeedItLabeledFilledIconButton(
    @StringRes labelId: Int,
    icon: ImageVector,
    contentDescription: String = "",
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    NeedItBaseIconButton(
        labelId = labelId,
        icon = icon,
        contentDescription = contentDescription,
        modifier = modifier,
        style = IconButtonStyle.Labeled,
        onClick = onClick
    )
}

@Composable
fun NeedItOutlinedIconButton(
    icon: ImageVector,
    contentDescription: String = "",
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    NeedItBaseIconButton(
        icon = icon,
        contentDescription = contentDescription,
        modifier = modifier,
        style = IconButtonStyle.Outlined,
        onClick = onClick
    )
}

@Preview
@Composable
private fun NeedItFilledIconButtonPreview() {
    NeedItTheme {
        NeedItFilledIconButton(
            icon = MaterialTheme.icons.AddFriend,
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun NeedItLabeledFilledIconButtonPreview() {
    NeedItTheme {
        NeedItLabeledFilledIconButton(
            labelId = R.string.button_remove,
            icon = MaterialTheme.icons.Delete,
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun NeedItOutlinedIconButtonPreview() {
    NeedItTheme {
        NeedItOutlinedIconButton(
            icon = MaterialTheme.icons.Delete,
            onClick = {}
        )
    }
}