package com.carlosdiestro.needit.core.design_system.components.navigation.tab_bar

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.height
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme

@Composable
fun NiScrollableTabBar(
    @StringRes tabLabelIds: List<Int>,
    modifier: Modifier = Modifier,
    selectedTabIndex: Int,
    containerColor: Color = NiTabBarSpecs.Color.transparent,
    onTabClick: (Int) -> Unit
) {
    PrimaryScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = containerColor,
        edgePadding = 0.dp,
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
        listOf(R.string.item_category_all).plus(tabLabelIds).forEachIndexed { index, labelId ->
            NiTab(
                labelId = labelId,
                selected = index == selectedTabIndex,
                onClick = { onTabClick(index) }
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
            tabLabelIds = tabs,
            selectedTabIndex = 0,
            onTabClick = {}
        )
    }
}