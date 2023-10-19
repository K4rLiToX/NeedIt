package com.carlosdiestro.needit.features.home

import androidx.annotation.StringRes
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
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.lists.HomeWishPlo
import com.carlosdiestro.needit.core.design_system.components.lists.WishCategoryPlo
import com.carlosdiestro.needit.core.design_system.theme.icons
import com.carlosdiestro.needit.domain.wishes.Wish
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class HomeDataState(
    val wishes: List<HomeWishPlo> = emptyList(),
    val categories: List<WishCategoryPlo> = emptyList(),
    val selectedWish: Wish? = null,
    val profilePictureUrl: String = ""
) {
    val noData: Boolean
        get() = wishes.isEmpty()

    @get:StringRes
    val selectedWishActionLabelId: Int
        get() {
            return if (selectedWish?.isShared == true) R.string.button_keep_private
            else R.string.button_share
        }
    val selectedWishActionIcon: ImageVector
        get() {
            return if (selectedWish?.isShared == true) MaterialTheme.icons.Lock
            else MaterialTheme.icons.Share
        }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun rememberHomeUiState(
    pagerState: PagerState,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    lazyGridState: LazyGridState = rememberLazyGridState(),
    wishActionsBottomSheetState: SheetState = rememberModalBottomSheetState(),
    deleteWishBottomSheetState: SheetState = rememberModalBottomSheetState(),
): HomeUiState {
    return remember(
        coroutineScope,
        lazyGridState,
        pagerState,
        wishActionsBottomSheetState,
        deleteWishBottomSheetState
    ) {
        HomeUiState(
            coroutineScope = coroutineScope,
            lazyGridState = lazyGridState,
            pagerState = pagerState,
            wishActionsBottomSheetState = wishActionsBottomSheetState,
            deleteWishBottomSheetState = deleteWishBottomSheetState
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Stable
class HomeUiState(
    val coroutineScope: CoroutineScope,
    val lazyGridState: LazyGridState,
    val pagerState: PagerState,
    val wishActionsBottomSheetState: SheetState,
    val deleteWishBottomSheetState: SheetState
) {
    var shouldOpenActionBottomSheet: Boolean by mutableStateOf(false)
        private set

    var shouldOpenRemoveWishBottomSheet: Boolean by mutableStateOf(false)
        private set

    val currentPage: Int
        get() = pagerState.currentPage

    val isScrollInProgress: Boolean
        get() = pagerState.isScrollInProgress

    fun openActionBottomSheet() {
        shouldOpenActionBottomSheet = true
    }

    fun closeActionBottomSheet() {
        shouldOpenActionBottomSheet = false
    }

    fun openRemoveWishBottomSheet() {
        shouldOpenRemoveWishBottomSheet = true
    }

    fun closeRemoveWishBottomSheet() {
        shouldOpenRemoveWishBottomSheet = false
    }

    fun scrollToPage(index: Int) {
        coroutineScope.launch {
            pagerState.animateScrollToPage(index)
        }
    }
}