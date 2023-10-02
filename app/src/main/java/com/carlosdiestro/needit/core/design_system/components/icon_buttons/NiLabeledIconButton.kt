package com.carlosdiestro.needit.core.design_system.components.icon_buttons

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icons

@Composable
fun NiLabeledIconButton(
    @StringRes labelId: Int,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    colors: IconButtonColors = NiIconButtonSpecs.Color.primary(),
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingXS),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NiIconButton(
            icon = icon,
            modifier = modifier,
            colors = colors,
            onClick = onClick
        )
        Text(
            text = stringResource(id = labelId),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun NiLabeledIconButtonsPreview() {
    NeedItTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NiLabeledIconButton(
                labelId = R.string.button_login,
                icon = MaterialTheme.icons.AddFriend,
                onClick = {}
            )
            NiLabeledIconButton(
                labelId = R.string.button_login,
                icon = MaterialTheme.icons.AddFriend,
                colors = NiIconButtonSpecs.Color.secondary(),
                onClick = {}
            )
            NiLabeledIconButton(
                labelId = R.string.button_login,
                icon = MaterialTheme.icons.AddFriend,
                colors = NiIconButtonSpecs.Color.neutral(),
                onClick = {}
            )
            NiLabeledIconButton(
                labelId = R.string.button_login,
                icon = MaterialTheme.icons.AddFriend,
                colors = NiIconButtonSpecs.Color.transparent(),
                onClick = {}
            )
        }
    }
}