package com.carlosdiestro.needit.features.upsert_item

import androidx.annotation.StringRes
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.lists.WishCategoryPlo

internal data class UpsertDataState(
    val imageUrl: String,
    val category: WishCategoryPlo,
) {
    @get:StringRes
    val titleHintId: Int
        get() {
            return if (category == WishCategoryPlo.Books)
                R.string.upsert_title_hint
            else
                R.string.upsert_name_hint
        }

    @get:StringRes
    val subtitleHintId: Int
        get() {
            return if (category == WishCategoryPlo.Books)
                R.string.upsert_author_hint
            else
                R.string.upsert_brand_hint
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun rememberUpsertUiState(
    scrollState: ScrollState = rememberScrollState(),
    topAppBarState: TopAppBarState = rememberTopAppBarState()
): UpsertUiState {
    return remember(
        scrollState,
        topAppBarState
    ) {
        UpsertUiState(
            scrollState = scrollState,
            topAppBarState = topAppBarState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Stable
internal class UpsertUiState(
    val scrollState: ScrollState,
    private val topAppBarState: TopAppBarState
) {
    val scrollBehavior: TopAppBarScrollBehavior
        @Composable get() = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)
}