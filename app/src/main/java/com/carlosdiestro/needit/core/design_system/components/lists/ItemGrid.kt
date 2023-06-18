package com.carlosdiestro.needit.core.design_system.components.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.carlosdiestro.needit.core.design_system.components.cards.WishCard
import com.carlosdiestro.needit.core.design_system.components.cards.WishPLO
import com.carlosdiestro.needit.core.design_system.theme.spacing

@Composable
fun NeedItWishGrid(
    state: LazyStaggeredGridState,
    wishes: List<WishPLO>,
    onItemClick: () -> Unit,
    onItemLongClick: () -> Unit
) {
    LazyVerticalStaggeredGrid(
        state = state,
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(
            horizontal = MaterialTheme.spacing.m,
            vertical = MaterialTheme.spacing.xl
        ),
        verticalItemSpacing = MaterialTheme.spacing.l,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(
            items = wishes,
            key = { it.id }
        ) { item ->
            WishCard(
                wish = item,
                onClick = onItemClick,
                onLongClick = onItemLongClick
            )
        }
    }
}