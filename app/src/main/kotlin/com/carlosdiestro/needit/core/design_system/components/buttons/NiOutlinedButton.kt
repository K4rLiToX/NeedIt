package com.carlosdiestro.needit.core.design_system.components.buttons

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedButton
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
fun NiOutlinedButton(
    @StringRes labelId: Int,
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
            labelId = labelId,
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
            labelId = R.string.button_login,
            onClick = {}
        )
        NiOutlinedButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            onClick = {}
        )
        NiOutlinedButton(
            labelId = R.string.button_login,
            trailIcon = Icons.AddFriend,
            onClick = {}
        )
        NiOutlinedButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            trailIcon = Icons.AddFriend,
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
            labelId = R.string.button_login,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiOutlinedButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiOutlinedButton(
            labelId = R.string.button_login,
            trailIcon = Icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiOutlinedButton(
            labelId = R.string.button_login,
            leadIcon = Icons.AddFriend,
            trailIcon = Icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
    }
}