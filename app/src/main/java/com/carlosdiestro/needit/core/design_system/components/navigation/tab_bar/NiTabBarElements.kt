package com.carlosdiestro.needit.core.design_system.components.navigation.tab_bar

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
internal fun NiTab(
    @StringRes labelId: Int,
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit
) {
    Tab(
        selected = selected,
        onClick = onClick,
        selectedContentColor = NiTabBarSpecs.TabContentColor.Selected,
        unselectedContentColor = NiTabBarSpecs.TabContentColor.Unselected,
        text = {
            Text(
                text = stringResource(id = labelId),
                style = MaterialTheme.typography.labelLarge,
                maxLines = 2,
                textAlign = TextAlign.Center
            )
        },
        modifier = modifier
            .height(NiTabBarSpecs.TabHeight)
    )
}

@Composable
internal fun NiTabIndicator(
    tabPositions: List<TabPosition>,
    selectedTabIndex: Int
) {
    if (tabPositions.isNotEmpty()) {
        TabRowDefaults.PrimaryIndicator(
            color = NiTabBarSpecs.IndicatorColor,
            shape = NiTabBarSpecs.IndicatorShape,
            modifier = Modifier.tabIndicatorOffset(
                tabPositions[selectedTabIndex]
            )
        )
    }
}