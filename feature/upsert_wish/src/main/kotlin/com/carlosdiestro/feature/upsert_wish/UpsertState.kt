package com.carlosdiestro.feature.upsert_wish

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
import com.carlosdiestro.design_system.i18n.Localization
import com.carlosdiestro.design_system.lists.WishCategoryPlo

internal data class UpsertDataState(
    val imageLocalPath: String,
    val category: WishCategoryPlo,
) {
    val titleHint: String
        @Composable get() {
            return if (category == WishCategoryPlo.Books)
                Localization.Upsert.TitleHint
            else
                Localization.Upsert.NameHint
        }

    val subtitleHint: String
        @Composable get() {
            return if (category == WishCategoryPlo.Books)
                Localization.Upsert.AuthorHint
            else
                Localization.Upsert.BrandHint
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