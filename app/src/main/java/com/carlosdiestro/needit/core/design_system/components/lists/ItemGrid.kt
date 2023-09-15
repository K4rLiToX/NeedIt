package com.carlosdiestro.needit.core.design_system.components.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.carlosdiestro.needit.core.design_system.components.cards.SimpleWishPLO
import com.carlosdiestro.needit.core.design_system.components.cards.WishCard
import com.carlosdiestro.needit.core.design_system.theme.dimensions

@Composable
fun NeedItWishGrid(
    state: LazyGridState,
    wishes: List<SimpleWishPLO>,
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit
) {
    LazyVerticalGrid(
        state = state,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            horizontal = MaterialTheme.dimensions.spacingM,
            vertical = MaterialTheme.dimensions.spacingM
        ),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingL),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingM),
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = wishes,
            key = { it.id }
        ) { item ->
            WishCard(
                title = item.title,
                imageUrl = item.imageUrl,
                isShared = item.isShared,
                onClick = {
                    onItemClick(item.id)
                },
                onLongClick = {
                    onItemLongClick(item.id)
                }
            )
        }
    }
}