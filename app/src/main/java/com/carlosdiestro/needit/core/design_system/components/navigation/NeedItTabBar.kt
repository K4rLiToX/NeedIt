package com.carlosdiestro.needit.core.design_system.components.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.spacing
import com.carlosdiestro.needit.core.design_system.theme.tabBar

enum class WishCategory(
    @StringRes val labelId: Int
) {
    All(labelId = R.string.item_category_all),
    Clothes(labelId = R.string.item_category_clothes),
    Footwear(labelId = R.string.item_category_footwear),
    Accessories(labelId = R.string.item_category_accessories),
    Grooming(labelId = R.string.item_category_grooming),
    Books(labelId = R.string.item_category_books),
    Tech(labelId = R.string.item_category_tech),
    Other(labelId = R.string.item_category_other)
}

fun WishCategory.toIntValue(): Int = this.ordinal
fun Int.toWishCategory(): WishCategory = WishCategory.values()[this]

enum class Gifts(
    @StringRes val labelId: Int
) {
    Reservations(labelId = R.string.gifts_tab_reservations),
    Groups(labelId = R.string.gifts_tab_groups)
}

@Composable
fun NeedItScrollableTabBar(
    selectedTabIndex: Int = 0,
    tabs: List<WishCategory>,
    currentPage: Int,
    containerColor: Color = MaterialTheme.colorScheme.background,
    scrollToPage: (Int) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = containerColor,
        edgePadding = MaterialTheme.spacing.m,
        indicator = { tabPositions ->
            if (tabPositions.isNotEmpty()) {
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(
                        tabPositions[currentPage]
                    )
                )
            }
        },
        modifier = Modifier
            .height(MaterialTheme.dimensions.tabBar.tabHeight),
        divider = {}
    ) {
        tabs.forEachIndexed { index, category ->
            NeedItTab(
                labelId = category.labelId,
                isSelected = index == selectedTabIndex,
                onClick = { scrollToPage(index) }
            )
        }
    }
}

@Composable
fun NeedItFixedTabBar(
    selectedTabIndex: Int = 0,
    onTabClicked: (Gifts, Int) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .height(MaterialTheme.dimensions.tabBar.tabHeight)
    ) {
        Gifts.values().toList().forEachIndexed { index, gift ->
            NeedItTab(
                labelId = gift.labelId,
                isSelected = index == selectedTabIndex,
                onClick = { onTabClicked(gift, index) }
            )
        }
    }
}

@Composable
private fun NeedItTab(
    @StringRes labelId: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Tab(
        selected = isSelected,
        onClick = onClick,
        selectedContentColor = MaterialTheme.colorScheme.primary,
        unselectedContentColor = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .height(MaterialTheme.dimensions.tabBar.tabHeight)
    ) {
        Text(
            text = stringResource(id = labelId),
            maxLines = 2,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun NeedItScrollableTabBarPreview() {
    NeedItTheme {
        NeedItScrollableTabBar(
            tabs = WishCategory.values().toList(),
            currentPage = 0,
            scrollToPage = {}
        )
    }
}

@Preview
@Composable
private fun NeedItFixedTabBarPreview() {
    NeedItTheme {
        NeedItFixedTabBar(
            onTabClicked = { _, _ -> }
        )
    }
}