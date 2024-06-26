package com.carlosdiestro.feature.home

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.design_system.fab.NiFab
import com.carlosdiestro.design_system.i18n.Localization
import com.carlosdiestro.design_system.icon_buttons.NiIconButtonSpecs
import com.carlosdiestro.design_system.icon_buttons.NiLabeledIconButton
import com.carlosdiestro.design_system.lists.HomeWishPlo
import com.carlosdiestro.design_system.lists.NiHomeWishList
import com.carlosdiestro.design_system.lists.WishCategoryPlo
import com.carlosdiestro.design_system.menus.NiDialog
import com.carlosdiestro.design_system.menus.NiWishActionMenu
import com.carlosdiestro.design_system.navigation.tab_bar.NiScrollableTabBar
import com.carlosdiestro.design_system.theme.dimensions
import com.carlosdiestro.design_system.theme.icons
import com.carlosdiestro.wish.domain.model.Wish

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun HomeRoute(
    onItemClick: (String) -> Unit,
    onUpdateClick: (Int, String) -> Unit,
    onCreateClick: () -> Unit,
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
        onUpdateClick = { onUpdateClick(0, dataState.selectedWish?.id.toString()) },
        onShareClick = viewModel::shareWish,
        onPrivateClick = viewModel::lockWish,
        onCreateClick = onCreateClick
    )
}

@Composable
private fun HomeScreen(
    dataState: HomeDataState,
    uiState: HomeUiState,
    onItemClick: (String) -> Unit,
    onItemLongClick: (String) -> Unit,
    onMenuDismiss: () -> Unit,
    onDeleteClick: () -> Unit,
    onUpdateClick: () -> Unit,
    onShareClick: () -> Unit,
    onPrivateClick: () -> Unit,
    onCreateClick: () -> Unit
) {
    val permissions = arrayOf(
        Manifest.permission.CAMERA,
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            Manifest.permission.READ_MEDIA_IMAGES
        else
            Manifest.permission.READ_EXTERNAL_STORAGE
    )
    val multiplePermissionsResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { result ->
            val areGranted = result.values.reduce { acc, next -> acc && next }
            if (areGranted) onCreateClick()
            else Unit
        }
    )

    Scaffold(
        floatingActionButton = {
            NiFab(
                icon = MaterialTheme.icons.Add,
                onClick = { multiplePermissionsResultLauncher.launch(permissions) }
            )
        },
        floatingActionButtonPosition = FabPosition.End
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
                selectedWishActionLabel = dataState.selectedWishActionLabel,
                selectedWishActionIcon = dataState.selectedWishActionIcon,
                isAnonymous = dataState.isAnonymous,
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
            text = Localization.Home.Empty,
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
    selectedWishActionLabel: String,
    selectedWishActionIcon: ImageVector,
    isAnonymous: Boolean,
    uiState: HomeUiState,
    onItemClick: (String) -> Unit,
    onItemLongClick: (String) -> Unit,
    onMenuDismiss: () -> Unit,
    onDeleteClick: () -> Unit,
    onUpdateClick: () -> Unit,
    onShareClick: () -> Unit,
    onPrivateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tabLabelIds by rememberSaveable(categories) {
        mutableStateOf(categories.map { it.labelId })
    }

    val wishlist by remember(uiState.currentPage, wishes) {
        mutableStateOf(
            if (uiState.currentPage == 0) {
                wishes
            } else {
                wishes.filter { it.category == categories[uiState.currentPage - 1] }
            }
        )
    }

    Column(
        modifier = modifier
    ) {
        NiScrollableTabBar(
            tabLabelIds = tabLabelIds,
            selectedTabIndex = uiState.currentPage,
            onTabClick = { index ->
                uiState.scrollToPage(index)
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
                selectedWishId = selectedWish?.id.toString(),
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
                label = Localization.Button.Remove,
                icon = MaterialTheme.icons.Delete,
                colors = NiIconButtonSpecs.Color.transparentPrimary(),
                onClick = {
                    uiState.closeActionBottomSheet()
                    uiState.openRemoveWishDialog()
                }
            )
            if (!isAnonymous) {
                NiLabeledIconButton(
                    label = Localization.Button.Update,
                    icon = MaterialTheme.icons.Edit,
                    colors = NiIconButtonSpecs.Color.transparentPrimary(),
                    onClick = {
                        onUpdateClick()
                        onMenuDismiss()
                    }
                )
            }
            NiLabeledIconButton(
                label = selectedWishActionLabel,
                icon = selectedWishActionIcon,
                colors = NiIconButtonSpecs.Color.transparentPrimary(),
                onClick = {
                    if (selectedWish?.isShared == true) onPrivateClick()
                    else onShareClick()
                    uiState.closeActionBottomSheet()
                    onMenuDismiss()
                }
            )
        }
    }

    if (uiState.shouldShowRemoveWishDialogs) {
        NiDialog(
            title = Localization.DeleteDialog.Title,
            body = Localization.DeleteDialog.Body,
            onConfirm = {
                onDeleteClick()
                uiState.closeRemoveWishDialog()
            },
            onDismiss = {
                uiState.closeRemoveWishDialog()
                onMenuDismiss()
            }
        )
    }
}

