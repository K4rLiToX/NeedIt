package com.carlosdiestro.needit.core.design_system.components.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun RowScope.NiNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    alwaysShowLabel: Boolean = true
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = NiNavigationSpecs.Color.SelectedIcon,
            unselectedIconColor = NiNavigationSpecs.Color.UnselectedIcon,
            selectedTextColor = NiNavigationSpecs.Color.SelectedLabel,
            unselectedTextColor = NiNavigationSpecs.Color.UnselectedLabel,
            indicatorColor = NiNavigationSpecs.Color.IndicatorColor
        )
    )
}