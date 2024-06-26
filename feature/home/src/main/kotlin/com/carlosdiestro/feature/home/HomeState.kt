package com.carlosdiestro.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import com.carlosdiestro.design_system.i18n.Localization
import com.carlosdiestro.design_system.lists.HomeWishPlo
import com.carlosdiestro.design_system.lists.WishCategoryPlo
import com.carlosdiestro.design_system.theme.icons
import com.carlosdiestro.wish.domain.model.Wish
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal data class HomeDataState(
    val wishes: List<HomeWishPlo> = emptyList(),
    val categories: List<WishCategoryPlo> = emptyList(),
    val selectedWish: Wish? = null,
    val isAnonymous: Boolean = true
) {
    val noData: Boolean
        get() = wishes.isEmpty()

    val selectedWishActionLabel: String
        @Composable
        get() {
            return if (selectedWish?.isShared == true) Localization.Button.KeepPrivate
            else Localization.Button.Share
        }
    val selectedWishActionIcon: ImageVector
        get() {
            return if (selectedWish?.isShared == true) MaterialTheme.icons.Lock
            else MaterialTheme.icons.Share
        }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun rememberHomeUiState(
    pagerState: PagerState,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    lazyGridState: LazyGridState = rememberLazyGridState(),
    wishActionsBottomSheetState: SheetState = rememberModalBottomSheetState()
): HomeUiState {
    return remember(
        coroutineScope,
        lazyGridState,
        pagerState,
        wishActionsBottomSheetState
    ) {
        HomeUiState(
            coroutineScope = coroutineScope,
            lazyGridState = lazyGridState,
            pagerState = pagerState,
            wishActionsBottomSheetState = wishActionsBottomSheetState
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Stable
internal class HomeUiState(
    val coroutineScope: CoroutineScope,
    val lazyGridState: LazyGridState,
    val pagerState: PagerState,
    val wishActionsBottomSheetState: SheetState
) {
    var shouldOpenActionBottomSheet: Boolean by mutableStateOf(false)
        private set

    var shouldShowRemoveWishDialogs: Boolean by mutableStateOf(false)
        private set

    val currentPage: Int
        get() = pagerState.currentPage

    fun openActionBottomSheet() {
        shouldOpenActionBottomSheet = true
    }

    fun closeActionBottomSheet() {
        shouldOpenActionBottomSheet = false
    }

    fun openRemoveWishDialog() {
        shouldShowRemoveWishDialogs = true
    }

    fun closeRemoveWishDialog() {
        shouldShowRemoveWishDialogs = false
    }

    fun scrollToPage(index: Int) {
        coroutineScope.launch {
            pagerState.animateScrollToPage(index)
        }
    }
}