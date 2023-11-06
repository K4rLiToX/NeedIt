package com.carlosdiestro.needit.core.design_system.components.buttons

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.TextButton
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
fun NiTextButton(
    @StringRes labelId: Int,
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
            labelId = labelId,
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
            labelId = R.string.button_login,
            onClick = {}
        )
        NiTextButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            onClick = {}
        )
        NiTextButton(
            labelId = R.string.button_login,
            trailIcon = Icons.AddFriend,
            onClick = {}
        )
        NiTextButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            trailIcon = Icons.AddFriend,
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
            labelId = R.string.button_login,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiTextButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiTextButton(
            labelId = R.string.button_login,
            trailIcon = Icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiTextButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            trailIcon = Icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
    }
}