package com.carlosdiestro.needit.core.ui.design_system.components.buttons

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.ui.design_system.theme.Icons
import com.carlosdiestro.needit.core.ui.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.ui.design_system.theme.button
import com.carlosdiestro.needit.core.ui.design_system.theme.dimensions

private enum class ButtonStyle {
    Outlined,
    Filled,
    Text
}

@Composable
private fun NeedItBaseButton(
    @StringRes labelId: Int,
    leadingIcon: ImageVector? = null,
    leadingIconContentDescription: String = "",
    trailingIcon: ImageVector? = null,
    trailingIconContentDescription: String = "",
    isEnabled: Boolean = true,
    style: ButtonStyle = ButtonStyle.Filled,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    when (style) {
        ButtonStyle.Filled -> {
            Button(
                onClick = onClick,
                enabled = isEnabled,
                modifier = modifier
                    .defaultMinSize(minHeight = MaterialTheme.dimensions.button.minHeight)
            ) {
                if (leadingIcon != null) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = leadingIconContentDescription,
                        modifier = Modifier
                            .size(ButtonDefaults.IconSize)
                    )
                    Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                }
                Text(
                    text = stringResource(labelId)
                )
                if (trailingIcon != null) {
                    Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = trailingIconContentDescription,
                        modifier = Modifier
                            .size(ButtonDefaults.IconSize)
                    )
                }
            }
        }

        ButtonStyle.Outlined -> {
            OutlinedButton(
                onClick = onClick,
                border = BorderStroke(
                    width = MaterialTheme.dimensions.button.outlinedButtonStrokeWidth,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = modifier
                    .defaultMinSize(minHeight = MaterialTheme.dimensions.button.minHeight)
            ) {
                Text(
                    text = stringResource(labelId)
                )
            }
        }

        ButtonStyle.Text -> {
            TextButton(
                onClick = onClick,
                modifier = modifier
            ) {
                Text(
                    text = stringResource(labelId)
                )
            }
        }
    }
}

@Composable
fun NeedItOutlinedButton(
    @StringRes labelId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    NeedItBaseButton(
        labelId = labelId,
        modifier = modifier,
        onClick = onClick,
        style = ButtonStyle.Outlined
    )
}

@Composable
fun NeedItFilledButton(
    @StringRes labelId: Int,
    leadingIcon: ImageVector? = null,
    leadingIconContentDescription: String = "",
    trailingIcon: ImageVector? = null,
    trailingIconContentDescription: String = "",
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    NeedItBaseButton(
        labelId = labelId,
        leadingIcon = leadingIcon,
        leadingIconContentDescription = leadingIconContentDescription,
        trailingIcon = trailingIcon,
        trailingIconContentDescription = trailingIconContentDescription,
        isEnabled = isEnabled,
        modifier = modifier,
        onClick = onClick
    )
}

@Composable
fun NeedItTextButton(
    @StringRes labelId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    NeedItBaseButton(
        labelId = labelId,
        onClick = onClick,
        style = ButtonStyle.Text,
        modifier = modifier
    )
}

@Preview
@Composable
fun NeedItOutlinedButtonPreview() {
    NeedItTheme {
        NeedItOutlinedButton(
            labelId = R.string.button_continue,
            onClick = {}
        )
    }
}

@Preview
@Composable
fun NeedItButtonPreview() {
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
                modifier = Modifier
                    .height(MaterialTheme.dimensions.button.largeHeight),
                onClick = {}
            )

        }
    }
}

@Preview
@Composable
fun NeedItTextButtonPreview() {
    NeedItTheme {
        NeedItTextButton(
            labelId = R.string.button_continue,
            onClick = {}
        )
    }
}
