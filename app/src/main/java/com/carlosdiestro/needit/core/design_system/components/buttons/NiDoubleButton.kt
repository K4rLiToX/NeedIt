package com.carlosdiestro.needit.core.design_system.components.buttons

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icons

@Composable
fun NiDoubleButton(
    leftButton: @Composable () -> Unit,
    rightButton: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.dimensions.spacingM,
            alignment = Alignment.CenterHorizontally
        ),
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
                    labelId = R.string.button_group_gift,
                    leadIcon = MaterialTheme.icons.Group,
                    height = NiButtonSpecs.Height.Large,
                    colors = NiButtonSpecs.Color.secondary(),
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(0.45F)
                )
            },
            rightButton = {
                NiFilledButton(
                    labelId = R.string.button_gift,
                    leadIcon = MaterialTheme.icons.Gifts,
                    height = NiButtonSpecs.Height.Large,
                    colors = NiButtonSpecs.Color.primary(),
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )
    }
}