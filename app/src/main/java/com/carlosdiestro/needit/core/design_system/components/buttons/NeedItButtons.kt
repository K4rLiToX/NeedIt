package com.carlosdiestro.needit.core.design_system.components.buttons

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.theme.Icons
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme

@Immutable
object ButtonSpecs {
    val DefaultHeight = ButtonDefaults.MinHeight
    val LargeHeight = 56.dp

    val IconSpacing = ButtonDefaults.IconSpacing
    val IconSize = ButtonDefaults.IconSize

    @Composable
    fun primaryColors(): ButtonColors {
        return ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    }

    @Composable
    fun inverseSurfaceColors(): ButtonColors {
        return ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.inverseSurface,
            contentColor = MaterialTheme.colorScheme.inverseOnSurface
        )
    }

    @Composable
    fun surfaceVariantColors(): ButtonColors {
        return ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }

    @Composable
    fun errorColors(): ButtonColors {
        return ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onError
        )
    }
}


@Composable
fun NeedItFilledButton(
    @StringRes labelId: Int,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    size: Dp = ButtonSpecs.DefaultHeight,
    colors: ButtonColors = ButtonSpecs.primaryColors(),
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = colors,
        enabled = isEnabled,
        modifier = modifier
            .height(size)
    ) {
        ButtonContent(
            labelId = labelId,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )
    }
}

@Composable
fun NeedItOutlinedButton(
    @StringRes labelId: Int,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    size: Dp = ButtonSpecs.DefaultHeight,
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier
            .height(size)
    ) {
        ButtonContent(
            labelId = labelId,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )
    }
}

@Composable
fun NeedItTextButton(
    @StringRes labelId: Int,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    size: Dp = ButtonSpecs.DefaultHeight,
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier
            .height(size)
    ) {
        ButtonContent(
            labelId = labelId,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )
    }
}

@Composable
private fun ButtonContent(
    @StringRes labelId: Int,
    leadingIcon: ImageVector?,
    trailingIcon: ImageVector?
) {
    if (leadingIcon != null) {
        Icon(
            imageVector = leadingIcon,
            contentDescription = "",
            modifier = Modifier
                .size(ButtonSpecs.IconSize)
        )
        Spacer(modifier = Modifier.width(ButtonSpecs.IconSpacing))
    }
    Text(text = stringResource(id = labelId))
    if (trailingIcon != null) {
        Spacer(modifier = Modifier.width(ButtonSpecs.IconSpacing))
        Icon(
            imageVector = trailingIcon,
            contentDescription = "",
            modifier = Modifier
                .size(ButtonSpecs.IconSize)
        )
    }
}

@Preview
@Composable
private fun NeedItOutlinedButtonPreview() {
    NeedItTheme {
        NeedItOutlinedButton(
            labelId = R.string.button_continue,
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun NeedItButtonPreview() {
    NeedItTheme {
        Column {
            NeedItFilledButton(
                labelId = R.string.button_continue,
                leadingIcon = Icons.AddFriend,
                onClick = {}
            )
            NeedItFilledButton(
                labelId = R.string.button_continue,
                trailingIcon = Icons.AddFriend,
                onClick = {}
            )
            NeedItFilledButton(
                labelId = R.string.button_continue,
                onClick = {}
            )
            NeedItFilledButton(
                labelId = R.string.button_continue,
                size = ButtonSpecs.LargeHeight,
                colors = ButtonSpecs.inverseSurfaceColors(),
                onClick = {}
            )

        }
    }
}

@Preview
@Composable
private fun NeedItTextButtonPreview() {
    NeedItTheme {
        NeedItTextButton(
            labelId = R.string.button_continue,
            onClick = {}
        )
    }
}
