package com.carlosdiestro.needit.core.design_system.components.navigation.tab_bar

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.height
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme

@Composable
fun NiTabBar(
    @StringRes labelIds: List<Int>,
    modifier: Modifier = Modifier,
    selectedTabIndex: Int,
    containerColor: Color = NiTabBarSpecs.Color.transparent,
    scrollToPage: (Int) -> Unit
) {
    PrimaryTabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = containerColor,
        indicator = { tabPositions ->
            NiTabIndicator(
                tabPositions = tabPositions,
                selectedTabIndex = selectedTabIndex
            )
        },
        divider = {},
        modifier = modifier
            .height(NiTabBarSpecs.TabHeight)
    ) {
        labelIds.forEachIndexed { index, labelId ->
            NiTab(
                labelId = labelId,
                selected = index == selectedTabIndex,
                onClick = { scrollToPage(index) }
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun NiTabBarPreview() {
    NeedItTheme {
        val tabs = listOf(
            R.string.gifts_tab_reservations,
            R.string.gifts_tab_groups
        )
        NiTabBar(
            labelIds = tabs,
            selectedTabIndex = 0,
            scrollToPage = {}
        )
    }
}