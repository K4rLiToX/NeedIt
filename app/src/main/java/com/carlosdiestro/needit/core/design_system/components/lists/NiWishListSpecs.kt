package com.carlosdiestro.needit.core.design_system.components.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import javax.annotation.concurrent.Immutable

@Immutable
object NiWishListSpecs {
    val CellMinWidth = 172.dp
    val Padding = PaddingValues(MaterialTheme.dimensions.spacingM)
    val VerticalSeparation = Arrangement.spacedBy(MaterialTheme.dimensions.spacingL)
    val HorizontalSeparation = Arrangement.spacedBy(MaterialTheme.dimensions.spacingM)
}