package com.carlosdiestro.design_system.buttons

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.carlosdiestro.design_system.theme.NeedItTheme
import com.carlosdiestro.design_system.theme.icons

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
            label = stringResource(id = labelId),
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
            labelId = -1,
            onClick = {}
        )
        NiOutlinedButton(
            labelId = -1,
            leadIcon = MaterialTheme.icons.AddFriend,
            onClick = {}
        )
        NiOutlinedButton(
            labelId = -1,
            trailIcon = MaterialTheme.icons.AddFriend,
            onClick = {}
        )
        NiOutlinedButton(
            labelId = -1,
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
            labelId = -1,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiOutlinedButton(
            labelId = -1,
            leadIcon = MaterialTheme.icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiOutlinedButton(
            labelId = -1,
            trailIcon = MaterialTheme.icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
        NiOutlinedButton(
            labelId = -1,
            leadIcon = MaterialTheme.icons.AddFriend,
            trailIcon = MaterialTheme.icons.AddFriend,
            height = NiButtonSpecs.Height.Large,
            onClick = {}
        )
    }
}