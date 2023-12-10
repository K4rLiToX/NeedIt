package com.carlosdiestro.design_system.buttons

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.carlosdiestro.design_system.i18n.Localization
import com.carlosdiestro.design_system.theme.NeedItTheme
import com.carlosdiestro.design_system.theme.dimensions
import com.carlosdiestro.design_system.theme.icons

@Composable
fun NiDoubleButton(
    modifier: Modifier = Modifier,
    leftButton: @Composable () -> Unit,
    rightButton: @Composable () -> Unit,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(
        space = MaterialTheme.dimensions.spacingM,
        alignment = Alignment.CenterHorizontally
    )
) {
    Row(
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        leftButton()
        rightButton()
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun NiDoubleButtonPreview() {
    NeedItTheme {
        NiDoubleButton(
            leftButton = {
                NiFilledButton(
                    label = Localization.Button.Cancel,
                    leadIcon = MaterialTheme.icons.Group,
                    height = NiButtonSpecs.Height.Large,
                    colors = NiButtonSpecs.Color.secondary(),
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(0.45F)
                )
            },
            rightButton = {
                NiFilledButton(
                    label = Localization.Button.Accept,
                    leadIcon = MaterialTheme.icons.GiftsOutlined,
                    height = NiButtonSpecs.Height.Large,
                    colors = NiButtonSpecs.Color.primary(),
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )
    }
}