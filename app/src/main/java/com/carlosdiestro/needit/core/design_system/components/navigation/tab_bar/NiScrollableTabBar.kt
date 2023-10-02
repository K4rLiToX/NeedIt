package com.carlosdiestro.needit.core.design_system.components.navigation.tab_bar

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.design_system.theme.dimensions

@Composable
fun NiScrollableTabBar(
    @StringRes labelIds: List<Int>,
    modifier: Modifier = Modifier,
    selectedTabIndex: Int,
    containerColor: Color = NiTabBarSpecs.Color.transparent,
    scrollToPage: (Int) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = containerColor,
        edgePadding = MaterialTheme.dimensions.spacingM,
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
internal fun NiScrollableTabBarPreview() {
    NeedItTheme {
        val tabs = listOf(
            R.string.item_category_all,
            R.string.item_category_clothes,
            R.string.item_category_footwear,
            R.string.item_category_accessories,
            R.string.item_category_grooming,
            R.string.item_category_books,
            R.string.item_category_tech,
            R.string.item_category_other
        )
        NiScrollableTabBar(
            labelIds = tabs,
            selectedTabIndex = 0,
            scrollToPage = {}
        )
    }
}