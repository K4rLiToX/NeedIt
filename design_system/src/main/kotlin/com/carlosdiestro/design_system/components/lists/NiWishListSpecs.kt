package com.carlosdiestro.design_system.components.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp
import com.carlosdiestro.design_system.theme.dimensions

@Immutable
object NiWishListSpecs {
    val CellMinWidth = 172.dp
    val Padding = PaddingValues(MaterialTheme.dimensions.spacingM)
    val VerticalSeparation = Arrangement.spacedBy(MaterialTheme.dimensions.spacingL)
    val HorizontalSeparation = Arrangement.spacedBy(MaterialTheme.dimensions.spacingM)
}