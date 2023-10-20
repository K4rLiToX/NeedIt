package com.carlosdiestro.needit.core.design_system.components.selectors.checkbox

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme

@Composable
fun NiCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
//        colors = CheckboxDefaults.colors(
//            checkedColor = MaterialTheme.colorScheme.primary,
//            checkmarkColor = MaterialTheme.colorScheme.onPrimary
//        ),
        modifier = modifier
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun NiCheckboxPreview() {
    NeedItTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NiCheckbox(
                checked = true,
                onCheckedChange = {}
            )
            NiCheckbox(
                checked = false,
                onCheckedChange = {}
            )
        }
    }
}