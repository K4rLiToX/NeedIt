package com.carlosdiestro.needit.core.design_system.components.cards

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.core.design_system.theme.dimensions

@Composable
fun NiSettingSectionCard(
    modifier: Modifier = Modifier,
    @StringRes titleId: Int,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape((24.dp)))
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp))
    ) {
        SettingSectionCardHeader(
            titleId = titleId,
            modifier = Modifier.fillMaxWidth()
        )
        content()
    }
}

@Composable
private fun SettingSectionCardHeader(
    modifier: Modifier = Modifier,
    @StringRes titleId: Int
) {
    Text(
        text = stringResource(id = titleId),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .padding(MaterialTheme.dimensions.spacingM)
    )
}