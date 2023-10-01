package com.carlosdiestro.needit.core.design_system.components.selectors

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme

@Composable
fun NiSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        enabled = enabled,
        modifier = modifier
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun NiSwitchPreview() {
    NeedItTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NiSwitch(
                checked = true,
                onCheckedChange = {},
            )
            NiSwitch(
                checked = true,
                onCheckedChange = {},
                enabled = false
            )
            NiSwitch(
                checked = false,
                onCheckedChange = {},
            )
            NiSwitch(
                checked = false,
                onCheckedChange = {},
                enabled = false
            )
        }
    }
}