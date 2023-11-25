package com.carlosdiestro.design_system.buttons

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.carlosdiestro.design_system.i18n.Localization
import com.carlosdiestro.design_system.theme.NeedItTheme
import com.carlosdiestro.design_system.theme.icons

@Composable
fun NiFilledButton(
    label: String,
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
            label = label,
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
            label = Localization.Button.Continue,
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            trailIcon = MaterialTheme.icons.AddFriend,
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            trailIcon = MaterialTheme.icons.AddFriend,
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
            label = Localization.Button.Continue,
            colors = NiButtonSpecs.Color.secondary(),
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.secondary(),
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            trailIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.secondary(),
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            trailIcon = MaterialTheme.icons.AddFriend,
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
            label = Localization.Button.Continue,
            colors = NiButtonSpecs.Color.neutral(),
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.neutral(),
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            trailIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.neutral(),
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            trailIcon = MaterialTheme.icons.AddFriend,
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
            label = Localization.Button.Continue,
            colors = NiButtonSpecs.Color.error(),
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.error(),
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            trailIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.error(),
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            trailIcon = MaterialTheme.icons.AddFriend,
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
            label = Localization.Button.Continue,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            trailIcon = MaterialTheme.icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            trailIcon = MaterialTheme.icons.AddFriend,
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
            label = Localization.Button.Continue,
            colors = NiButtonSpecs.Color.secondary(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.secondary(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            trailIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.secondary(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            trailIcon = MaterialTheme.icons.AddFriend,
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
            label = Localization.Button.Continue,
            colors = NiButtonSpecs.Color.neutral(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.neutral(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            trailIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.neutral(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            trailIcon = MaterialTheme.icons.AddFriend,
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
            label = Localization.Button.Continue,
            colors = NiButtonSpecs.Color.error(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.error(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            trailIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.error(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            trailIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.error(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
    }
}