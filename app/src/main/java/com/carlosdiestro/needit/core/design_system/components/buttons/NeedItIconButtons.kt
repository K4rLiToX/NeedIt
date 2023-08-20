package com.carlosdiestro.needit.core.design_system.components.buttons

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.texts.LabelLarge
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icons

@Immutable
object IconButtonSpecs {
    val StrokeWidth = 1.dp

    val containerSmall = 32.dp
    val containerMedium = 40.dp
    val containerLarge = 48.dp
    val containerExtraLarge = 56.dp

    val iconExtraExtraSmall = 16.dp
    val iconExtraSmall = 20.dp
    val iconSmall = 24.dp
    val iconMedium = 40.dp
    val iconLarge = 48.dp
    val iconExtraLarge = 56.dp
    val iconExtraExtraLarge = 80.dp

    @Composable
    fun defaultColors(): IconButtonColors {
        return IconButtonDefaults.filledIconButtonColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        )
    }

    @Composable
    fun borderStroke(): BorderStroke {
        return BorderStroke(
            width = StrokeWidth,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun NeedItFilledIconButton(
    icon: ImageVector,
    colors: IconButtonColors = IconButtonSpecs.defaultColors(),
    containerSize: Dp = IconButtonSpecs.containerLarge,
    iconSize: Dp = IconButtonSpecs.iconSmall,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        colors = colors,
        modifier = modifier
            .clip(CircleShape)
            .size(containerSize)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            modifier = Modifier
                .size(iconSize)
        )
    }
}

@Composable
fun NeedItOutlinedIconButton(
    icon: ImageVector,
    containerSize: Dp = IconButtonSpecs.containerLarge,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedIconButton(
        onClick = onClick,
        border = IconButtonSpecs.borderStroke(),
        modifier = modifier
            .size(containerSize)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun NeedItLabeledIconButton(
    icon: ImageVector,
    @StringRes labelId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingXS),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NeedItFilledIconButton(
            icon = icon,
            modifier = modifier,
            onClick = onClick
        )
        LabelLarge(
            labelId = labelId,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
    }

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
        NeedItLabeledIconButton(
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