package com.carlosdiestro.needit.core.design_system.components.buttons

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.theme.Icons
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme

@Composable
fun NiFilledButton(
    @StringRes labelId: Int,
    modifier: Modifier = Modifier,
    leadIcon: ImageVector? = null,
    trailIcon: ImageVector? = null,
    height: Dp = NiButtonSpecs.Height.Default,
    colors: ButtonColors = NiButtonSpecs.Color.primary(),
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = colors,
        enabled = enabled,
        modifier = modifier
            .height(height)
    ) {
        NiButtonContent(
            labelId = labelId,
            leadIcon = leadIcon,
            trailIcon = trailIcon
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun NiFilledButtonsPreview() {
    NeedItTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NiPrimaryButtons()
            NiSecondaryButtons()
            NiNeutralButtons()
            NiErrorButtons()
            NiLargePrimaryButtons()
            NiLargeSecondaryButtons()
            NiLargeNeutralButtons()
            NiLargeErrorButtons()
        }
    }
}

@Composable
internal fun NiPrimaryButtons() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NiFilledButton(
            labelId = R.string.button_login,
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            trailIcon = Icons.AddFriend,
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            trailIcon = Icons.AddFriend,
            onClick = {}
        )
    }
}

@Composable
internal fun NiSecondaryButtons() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NiFilledButton(
            labelId = R.string.button_login,
            colors = NiButtonSpecs.Color.secondary(),
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            colors = NiButtonSpecs.Color.secondary(),
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            trailIcon = Icons.AddFriend,
            colors = NiButtonSpecs.Color.secondary(),
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            trailIcon = Icons.AddFriend,
            colors = NiButtonSpecs.Color.secondary(),
            onClick = {}
        )
    }
}

@Composable
internal fun NiNeutralButtons() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NiFilledButton(
            labelId = R.string.button_login,
            colors = NiButtonSpecs.Color.neutral(),
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            colors = NiButtonSpecs.Color.neutral(),
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            trailIcon = Icons.AddFriend,
            colors = NiButtonSpecs.Color.neutral(),
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            trailIcon = Icons.AddFriend,
            colors = NiButtonSpecs.Color.neutral(),
            onClick = {}
        )
    }
}

@Composable
internal fun NiErrorButtons() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NiFilledButton(
            labelId = R.string.button_login,
            colors = NiButtonSpecs.Color.error(),
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            colors = NiButtonSpecs.Color.error(),
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            trailIcon = Icons.AddFriend,
            colors = NiButtonSpecs.Color.error(),
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            trailIcon = Icons.AddFriend,
            colors = NiButtonSpecs.Color.error(),
            onClick = {}
        )
    }
}

@Composable
internal fun NiLargePrimaryButtons() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NiFilledButton(
            labelId = R.string.button_login,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            trailIcon = Icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            trailIcon = Icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
    }
}

@Composable
internal fun NiLargeSecondaryButtons() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NiFilledButton(
            labelId = R.string.button_login,
            colors = NiButtonSpecs.Color.secondary(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            colors = NiButtonSpecs.Color.secondary(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            trailIcon = Icons.AddFriend,
            colors = NiButtonSpecs.Color.secondary(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            trailIcon = Icons.AddFriend,
            colors = NiButtonSpecs.Color.secondary(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
    }
}

@Composable
internal fun NiLargeNeutralButtons() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NiFilledButton(
            labelId = R.string.button_login,
            colors = NiButtonSpecs.Color.neutral(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            colors = NiButtonSpecs.Color.neutral(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            trailIcon = Icons.AddFriend,
            colors = NiButtonSpecs.Color.neutral(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            trailIcon = Icons.AddFriend,
            colors = NiButtonSpecs.Color.neutral(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
    }
}

@Composable
internal fun NiLargeErrorButtons() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NiFilledButton(
            labelId = R.string.button_login,
            colors = NiButtonSpecs.Color.error(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            colors = NiButtonSpecs.Color.error(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            trailIcon = Icons.AddFriend,
            colors = NiButtonSpecs.Color.error(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            trailIcon = Icons.AddFriend,
            colors = NiButtonSpecs.Color.error(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
    }
}