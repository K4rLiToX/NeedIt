package com.carlosdiestro.design_system.components.cards

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.carlosdiestro.design_system.theme.dimensions
import com.carlosdiestro.design_system.theme.shape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NiAccountExtraInfoCard(
    modifier: Modifier = Modifier,
    @StringRes labelId: Int,
    value: String,
    icon: ImageVector
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp)
        ),
        shape = MaterialTheme.shapes.medium,
        onClick = {},
        modifier = modifier
            .padding(MaterialTheme.dimensions.spacingL)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(MaterialTheme.shape.full)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Icon(
                imageVector = icon,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = stringResource(id = labelId)
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingXXL))
        Text(
            text = stringResource(id = labelId),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingXS))
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}