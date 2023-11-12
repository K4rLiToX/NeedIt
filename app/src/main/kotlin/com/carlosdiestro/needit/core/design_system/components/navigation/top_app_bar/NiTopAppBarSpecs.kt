package com.carlosdiestro.needit.core.design_system.components.navigation.top_app_bar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp

@Immutable
object NiTopAppBarSpecs {
    @Immutable
    object Color {
        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun transparent(): TopAppBarColors {
            return TopAppBarDefaults.topAppBarColors(
                containerColor = androidx.compose.ui.graphics.Color.Transparent,
                titleContentColor = MaterialTheme.colorScheme.onSurface,
                scrolledContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp)
            )
        }

        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun neutral(): TopAppBarColors {
            return TopAppBarDefaults.topAppBarColors(
                containerColor = androidx.compose.ui.graphics.Color.Transparent,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}