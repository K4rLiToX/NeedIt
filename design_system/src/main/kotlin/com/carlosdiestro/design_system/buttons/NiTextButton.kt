package com.carlosdiestro.design_system.buttons

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
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
fun NiTextButton(
    label: String,
    modifier: Modifier = Modifier,
    leadIcon: ImageVector? = null,
    trailIcon: ImageVector? = null,
    height: Dp = NiButtonSpecs.Height.Default,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
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

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun NiTextButtonsPreview() {
    NeedItTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NiTextButtons()
            NiLargeTextButtons()
        }
    }
}

@Composable
internal fun NiTextButtons() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NiTextButton(
            label = Localization.Button.Ok,
            onClick = {}
        )
        NiTextButton(
            label = Localization.Button.Ok,
            leadIcon = MaterialTheme.icons.AddFriend,
            onClick = {}
        )
        NiTextButton(
            label = Localization.Button.Ok,
            trailIcon = MaterialTheme.icons.AddFriend,
            onClick = {}
        )
        NiTextButton(
            label = Localization.Button.Ok,
            leadIcon = MaterialTheme.icons.AddFriend,
            trailIcon = MaterialTheme.icons.AddFriend,
            onClick = {}
        )
    }
}

@Composable
internal fun NiLargeTextButtons() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NiTextButton(
            label = Localization.Button.Ok,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiTextButton(
            label = Localization.Button.Ok,
            leadIcon = MaterialTheme.icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiTextButton(
            label = Localization.Button.Ok,
            trailIcon = MaterialTheme.icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiTextButton(
            label = Localization.Button.Ok,
            leadIcon = MaterialTheme.icons.AddFriend,
            trailIcon = MaterialTheme.icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
    }
}