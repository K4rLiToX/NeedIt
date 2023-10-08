package com.carlosdiestro.needit.core.design_system.components.menus

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import javax.annotation.concurrent.Immutable

@Immutable
object NiMenuSpecs {
    val NoDragHandle: @Composable () -> Unit
        @Composable get() = {}
    val StraightCorners = RoundedCornerShape(0)
}