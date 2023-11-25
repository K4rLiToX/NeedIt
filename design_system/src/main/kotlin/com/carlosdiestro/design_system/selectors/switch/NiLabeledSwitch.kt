package com.carlosdiestro.design_system.selectors.switch

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.carlosdiestro.design_system.i18n.Localization
import com.carlosdiestro.design_system.theme.NeedItTheme
import com.carlosdiestro.design_system.theme.dimensions

@Composable
fun NiLabeledSwitch(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(
                horizontal = MaterialTheme.dimensions.spacingM,
                vertical = MaterialTheme.dimensions.spacingXXS
            )
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = if (enabled) MaterialTheme.colorScheme.onSurface else MaterialTheme
                .colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.width(MaterialTheme.dimensions.spacingM))
        NiSwitch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun NiLabeledSwitchPreview() {
    NeedItTheme {
        NiLabeledSwitch(
            label = Localization.Settings.NotificationsSectionFriendRequests,
            checked = true,
            onCheckedChange = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}