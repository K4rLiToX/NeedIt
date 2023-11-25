package com.carlosdiestro.design_system.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.carlosdiestro.design_system.theme.dimensions
import com.carlosdiestro.design_system.theme.shape

@Composable
fun NiSettingSectionCard(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .clip(MaterialTheme.shape.medium)
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp))
    ) {
        SettingSectionCardHeader(
            title = title,
            modifier = Modifier.fillMaxWidth()
        )
        content()
    }
}

@Composable
private fun SettingSectionCardHeader(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .padding(MaterialTheme.dimensions.spacingM)
    )
}