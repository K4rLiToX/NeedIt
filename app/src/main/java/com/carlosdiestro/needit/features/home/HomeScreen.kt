package com.carlosdiestro.needit.features.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItFilledIconButton
import com.carlosdiestro.needit.core.design_system.components.cards.SimpleWishPLO
import com.carlosdiestro.needit.core.design_system.components.lists.NeedItWishGrid
import com.carlosdiestro.needit.core.design_system.components.menu.NeedItSortingMenu
import com.carlosdiestro.needit.core.design_system.components.menu.SortType
import com.carlosdiestro.needit.core.design_system.components.navigation.NeedItScrollableTabBar
import com.carlosdiestro.needit.core.design_system.components.navigation.NeedItTopAppBar
import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory
import com.carlosdiestro.needit.core.design_system.theme.icons
import com.carlosdiestro.needit.core.design_system.theme.spacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    coroutineScope: CoroutineScope,
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(
        state = state,
        coroutineScope = coroutineScope,
        onItemClick = onItemClick,
        onItemLongClick = onItemLongClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    state: HomeUiState,
    coroutineScope: CoroutineScope,
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit
) {
    Scaffold(
        topBar = {
            NeedItTopAppBar(
                title = stringResource(id = R.string.home_title),
            )
        }
    ) {
        val baseModifier = Modifier
            .fillMaxSize()
            .padding(it)
        if (state.isEmpty) HomeEmptyState(baseModifier)
        else HomeSuccessState(
            wishes = state.wishes,
            tabs = state.tabs,
            coroutineScope = coroutineScope,
            onItemClick = onItemClick,
            onItemLongClick = onItemLongClick,
            modifier = baseModifier
        )
    }
}

@Composable
private fun HomeEmptyState(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.home_empty),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(MaterialTheme.spacing.xxl)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeSuccessState(
    wishes: List<SimpleWishPLO>,
    tabs: List<WishCategory>,
    coroutineScope: CoroutineScope,
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = tabs::size
    )
    val gridState = rememberLazyGridState()
    val pagerWishlist by remember(pagerState.currentPage, pagerState.currentPage != 0) {
        mutableStateOf(
            wishes.filter {
                it.category == tabs[pagerState.currentPage]
            }
        )
    }
    Column(
        modifier = modifier
    ) {
        NeedItScrollableTabBar(
            selectedTabIndex = pagerState.currentPage,
            tabs = tabs,
            currentPage = pagerState.currentPage,
            scrollToPage = { index ->
                coroutineScope.launch {
                    pagerState.scrollToPage(index)
                }
            }
        )
        HorizontalPager(
            state = pagerState,
            key = { it }
        ) {
            NeedItWishGrid(
                state = gridState,
                wishes = if (pagerState.currentPage == 0) wishes else pagerWishlist,
                onItemClick = onItemClick,
                onItemLongClick = onItemLongClick
            )
        }
    }
}

