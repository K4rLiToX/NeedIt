package com.carlosdiestro.design_system.buttons

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
fun NiOutlinedButton(
    label: String,
    modifier: Modifier = Modifier,
    leadIcon: ImageVector? = null,
    trailIcon: ImageVector? = null,
    height: Dp = NiButtonSpecs.Height.Default,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButton(
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
internal fun NiOutlinedButtonsPreview() {
    NeedItTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NiOutlinedButtons()
            NiLargeOutlinedButtons()
        }
    }
}

@Composable
internal fun NiOutlinedButtons() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NiOutlinedButton(
            label = Localization.Button.Continue,
            onClick = {}
        )
        NiOutlinedButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            onClick = {}
        )
        NiOutlinedButton(
            label = Localization.Button.Continue,
            trailIcon = MaterialTheme.icons.AddFriend,
            onClick = {}
        )
        NiOutlinedButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            trailIcon = MaterialTheme.icons.AddFriend,
            onClick = {}
        )
    }
}

@Composable
internal fun NiLargeOutlinedButtons() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NiOutlinedButton(
            label = Localization.Button.Continue,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiOutlinedButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiOutlinedButton(
            label = Localization.Button.Continue,
            trailIcon = MaterialTheme.icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiOutlinedButton(
            label = Localization.Button.Continue,
            leadIcon = MaterialTheme.icons.AddFriend,
            trailIcon = MaterialTheme.icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
    }
}