package com.carlosdiestro.needit.core.design_system.components.menus

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import com.carlosdiestro.needit.core.design_system.theme.shape
import javax.annotation.concurrent.Immutable

@Immutable
object NiMenuSpecs {
    val NoDragHandle: @Composable () -> Unit
        @Composable get() = {}
    val StraightCorners: Shape
        @Composable get() = MaterialTheme.shape.none
    val RoundedCorners: RoundedCornerShape
        @Composable get() = MaterialTheme.shape.medium
}