package com.carlosdiestro.design_system.components.buttons

import android.content.res.Configuration
import androidx.annotation.StringRes
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
import com.carlosdiestro.design_system.theme.NeedItTheme
import com.carlosdiestro.design_system.theme.icons

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
            labelId = -1,
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
            leadIcon = MaterialTheme.icons.AddFriend,
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
            trailIcon = MaterialTheme.icons.AddFriend,
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
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
            labelId = -1,
            colors = NiButtonSpecs.Color.secondary(),
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
            leadIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.secondary(),
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
            trailIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.secondary(),
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
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
            labelId = -1,
            colors = NiButtonSpecs.Color.neutral(),
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
            leadIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.neutral(),
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
            trailIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.neutral(),
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
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
            labelId = -1,
            colors = NiButtonSpecs.Color.error(),
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
            leadIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.error(),
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
            trailIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.error(),
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
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
            labelId = -1,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
            leadIcon = MaterialTheme.icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
            trailIcon = MaterialTheme.icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
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
            labelId = -1,
            colors = NiButtonSpecs.Color.secondary(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
            leadIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.secondary(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
            trailIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.secondary(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
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
            labelId = -1,
            colors = NiButtonSpecs.Color.neutral(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
            leadIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.neutral(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
            trailIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.neutral(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
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
            labelId = -1,
            colors = NiButtonSpecs.Color.error(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
            leadIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.error(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
            trailIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.error(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiFilledButton(
            labelId = -1,
            leadIcon = MaterialTheme.icons.AddFriend,
            trailIcon = MaterialTheme.icons.AddFriend,
            colors = NiButtonSpecs.Color.error(),
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
    }
}