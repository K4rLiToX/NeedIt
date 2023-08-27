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
import androidx.compose.runtime.getValue
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
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItLabeledIconButton
import com.carlosdiestro.needit.core.design_system.components.cards.SimpleWishPLO
import com.carlosdiestro.needit.core.design_system.components.dialogs.NeedItDialog
import com.carlosdiestro.needit.core.design_system.components.lists.NeedItWishGrid
import com.carlosdiestro.needit.core.design_system.components.menu.NeedItItemActionMenu
import com.carlosdiestro.needit.core.design_system.components.navigation.NeedItScrollableTabBar
import com.carlosdiestro.needit.core.design_system.components.navigation.NeedItTopAppBar
import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icons
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    coroutineScope: CoroutineScope,
    onItemClick: (Long) -> Unit,
    onUpdateClick: (String, Int, Long) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(
        state = state,
        coroutineScope = coroutineScope,
        onItemClick = onItemClick,
        onItemLongClick = viewModel::onSelectedWish,
        onDeleteClick = viewModel::deleteWish,
        onUpdateClick = { onUpdateClick(" ", 0, viewModel.selectedWishId!!) },
        onShareClick = viewModel::uploadWish,
        onPrivateClick = viewModel::privateWish
    )
}

@Composable
private fun HomeScreen(
    state: HomeUiState,
    coroutineScope: CoroutineScope,
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit,
    onDeleteClick: () -> Unit,
    onUpdateClick: () -> Unit,
    onShareClick: () -> Unit,
    onPrivateClick: () -> Unit
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
            selectedWishIsShared = state.wishIsShared,
            coroutineScope = coroutineScope,
            onItemClick = onItemClick,
            onItemLongClick = onItemLongClick,
            onDeleteClick = onDeleteClick,
            onUpdateClick = onUpdateClick,
            onShareClick = onShareClick,
            onPrivateClick = onPrivateClick,
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
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(MaterialTheme.dimensions.spacingXXL)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun HomeSuccessState(
    wishes: List<SimpleWishPLO>,
    tabs: List<WishCategory>,
    selectedWishIsShared: Boolean,
    coroutineScope: CoroutineScope,
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit,
    onDeleteClick: () -> Unit,
    onUpdateClick: () -> Unit,
    onShareClick: () -> Unit,
    onPrivateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val wishActionsBottomSheetState = rememberModalBottomSheetState()
    var openActionBottomSheet by rememberSaveable {
        mutableStateOf(false)
    }
    val deleteWishBottomSheetState = rememberModalBottomSheetState()
    var openRemoveWishBottomSheet by rememberSaveable {
        mutableStateOf(false)
    }
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
                onItemLongClick = { id ->
                    openActionBottomSheet = true
                    onItemLongClick(id)
                }
            )
        }
    }
    if (openActionBottomSheet) {
        val labelId =
            if (selectedWishIsShared) R.string.button_keep_private else R.string.button_share
        val icon = if (selectedWishIsShared) MaterialTheme.icons.Lock else MaterialTheme.icons.Share
        NeedItItemActionMenu(
            sheetState = wishActionsBottomSheetState,
            onDismiss = { openActionBottomSheet = false },
            actions = {
                NeedItLabeledIconButton(
                    labelId = R.string.button_remove,
                    icon = MaterialTheme.icons.Delete,
                    onClick = {
                        openActionBottomSheet = false
                        openRemoveWishBottomSheet = true
                    }
                )
                NeedItLabeledIconButton(
                    labelId = R.string.button_update,
                    icon = MaterialTheme.icons.Edit,
                    onClick = {
                        onUpdateClick()
                        openActionBottomSheet = false
                    }
                )
                NeedItLabeledIconButton(
                    labelId = labelId,
                    icon = icon,
                    onClick = {
                        if (selectedWishIsShared) onPrivateClick()
                        else onShareClick()
                        openActionBottomSheet = false
                    }
                )
            }
        )
    }

    if (openRemoveWishBottomSheet) {
        NeedItDialog(
            titleId = R.string.delete_dialog_title,
            bodyId = R.string.delete_dialog_body,
            sheetState = deleteWishBottomSheetState,
            onDismiss = { openRemoveWishBottomSheet = false },
            onAccept = {
                onDeleteClick()
                openRemoveWishBottomSheet = false
            }
        )
    }
}

