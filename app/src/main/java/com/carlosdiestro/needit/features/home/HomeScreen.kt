package com.carlosdiestro.needit.features.home

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.buttons.NiButtonSpecs
import com.carlosdiestro.needit.core.design_system.components.buttons.NiFilledButton
import com.carlosdiestro.needit.core.design_system.components.dialogs.NiDialog
import com.carlosdiestro.needit.core.design_system.components.icon_buttons.NiLabeledIconButton
import com.carlosdiestro.needit.core.design_system.components.lists.HomeWishPlo
import com.carlosdiestro.needit.core.design_system.components.lists.NiHomeWishList
import com.carlosdiestro.needit.core.design_system.components.lists.WishCategoryPlo
import com.carlosdiestro.needit.core.design_system.components.menus.NiWishActionMenu
import com.carlosdiestro.needit.core.design_system.components.navigation.tab_bar.NiScrollableTabBar
import com.carlosdiestro.needit.core.design_system.components.navigation.top_app_bar.NiMainTopAppBar
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icons
import com.carlosdiestro.needit.domain.wishes.Wish

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeRoute(
    onItemClick: (Long) -> Unit,
    onUpdateClick: (String, Int, Long) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val dataState by viewModel.state.collectAsStateWithLifecycle()
    val uiState = rememberHomeUiState(
        pagerState = rememberPagerState(
            initialPage = 0,
            pageCount = { dataState.categories.size + 1 }
        )
    )

    HomeScreen(
        dataState = dataState,
        uiState = uiState,
        onItemClick = onItemClick,
        onItemLongClick = viewModel::onSelectedWish,
        onMenuDismiss = viewModel::clearWishSelection,
        onDeleteClick = viewModel::deleteWish,
        onUpdateClick = { onUpdateClick(" ", 0, dataState.selectedWish?.id!!) },
        onShareClick = viewModel::uploadWish,
        onPrivateClick = viewModel::privateWish
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    dataState: HomeDataState,
    uiState: HomeUiState,
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit,
    onMenuDismiss: () -> Unit,
    onDeleteClick: () -> Unit,
    onUpdateClick: () -> Unit,
    onShareClick: () -> Unit,
    onPrivateClick: () -> Unit
) {
    Scaffold(
        topBar = {
            NiMainTopAppBar(
                accountImageUrl = dataState.profilePictureUrl,
                onNotificationClick = {},
                onAccountClick = {}
            )
        }
    ) {
        if (dataState.noData)
            HomeEmptyState(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
        else
            HomeSuccessState(
                wishes = dataState.wishes,
                categories = dataState.categories,
                selectedWish = dataState.selectedWish,
                selectedWishActionLabelId = dataState.selectedWishActionLabelId,
                selectedWishActionIcon = dataState.selectedWishActionIcon,
                uiState = uiState,
                onItemClick = onItemClick,
                onItemLongClick = onItemLongClick,
                onMenuDismiss = onMenuDismiss,
                onDeleteClick = onDeleteClick,
                onUpdateClick = onUpdateClick,
                onShareClick = onShareClick,
                onPrivateClick = onPrivateClick,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
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
    wishes: List<HomeWishPlo>,
    categories: List<WishCategoryPlo>,
    selectedWish: Wish?,
    @StringRes selectedWishActionLabelId: Int,
    selectedWishActionIcon: ImageVector,
    uiState: HomeUiState,
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit,
    onMenuDismiss: () -> Unit,
    onDeleteClick: () -> Unit,
    onUpdateClick: () -> Unit,
    onShareClick: () -> Unit,
    onPrivateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(uiState.selectedTabIndex) {
        uiState.scrollToPage(uiState.selectedTabIndex)
    }

    LaunchedEffect(uiState.currentPage, uiState.isScrollInProgress) {
        if (!uiState.isScrollInProgress) {
            uiState.updateSelectedTabIndex(uiState.currentPage)
        }
    }

    val tabLabelIds by rememberSaveable(categories) {
        mutableStateOf(categories.map { it.labelId })
    }

    val wishlist by rememberSaveable(uiState.selectedTabIndex, wishes) {
        mutableStateOf(
            if (uiState.selectedTabIndex == 0) {
                wishes
            } else {
                wishes.filter { it.category == categories[uiState.selectedTabIndex - 1] }
            }
        )
    }

    Column(
        modifier = modifier
    ) {
        NiScrollableTabBar(
            tabLabelIds = tabLabelIds,
            selectedTabIndex = uiState.selectedTabIndex,
            onTabClick = { index ->
                uiState.updateSelectedTabIndex(index)
            },
            modifier = Modifier.fillMaxWidth()
        )
        HorizontalPager(
            state = uiState.pagerState,
            key = { it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
        ) {
            NiHomeWishList(
                onItemClick = onItemClick,
                onItemLongClick = { id ->
                    uiState.openActionBottomSheet()
                    onItemLongClick(id)
                },
                lazyGridState = uiState.lazyGridState,
                wishes = wishlist,
                selectedWishId = selectedWish?.id,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    if (uiState.shouldOpenActionBottomSheet) {
        NiWishActionMenu(
            sheetState = uiState.wishActionsBottomSheetState,
            onDismiss = {
                uiState.closeActionBottomSheet()
                onMenuDismiss()
            }
        ) {
            NiLabeledIconButton(
                labelId = R.string.button_remove,
                icon = MaterialTheme.icons.Delete,
                onClick = {
                    uiState.closeActionBottomSheet()
                    uiState.openRemoveWishBottomSheet()
                }
            )
            NiLabeledIconButton(
                labelId = R.string.button_update,
                icon = MaterialTheme.icons.Edit,
                onClick = {
                    onUpdateClick()
                    uiState.closeActionBottomSheet()
                }
            )
            NiLabeledIconButton(
                labelId = selectedWishActionLabelId,
                icon = selectedWishActionIcon,
                onClick = {
                    if (selectedWish?.isShared == true) onPrivateClick()
                    else onShareClick()
                }
            )
        }
    }

    if (uiState.shouldOpenRemoveWishBottomSheet) {
        NiDialog(
            titleId = R.string.delete_dialog_title,
            bodyId = R.string.delete_dialog_body,
            sheetState = uiState.deleteWishBottomSheetState,
            onDismiss = {
                uiState.closeRemoveWishBottomSheet()
                onMenuDismiss()
            }
        ) {
            NiFilledButton(
                labelId = R.string.button_reject,
                colors = NiButtonSpecs.Color.neutral(),
                height = NiButtonSpecs.Height.Large,
                onClick = {
                    uiState.closeRemoveWishBottomSheet()
                    onMenuDismiss()
                },
                modifier = Modifier.fillMaxWidth(0.48F)
            )
            NiFilledButton(
                labelId = R.string.button_continue,
                colors = NiButtonSpecs.Color.error(),
                height = NiButtonSpecs.Height.Large,
                onClick = {
                    onDeleteClick()
                    uiState.closeRemoveWishBottomSheet()
                },
                modifier = Modifier.weight(1F)
            )
        }
    }
}

