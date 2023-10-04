package com.carlosdiestro.needit.core.design_system.components.lists

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.carlosdiestro.needit.core.design_system.components.cards.NiWishCard
import com.carlosdiestro.needit.domain.wishes.WishCategory

data class HomeWishPLO(
    val id: Long,
    val imageUrl: String,
    val shared: Boolean,
    val category: WishCategory
)

@Composable
fun NiHomeWishList(
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    state: LazyGridState,
    wishes: List<HomeWishPLO>
) {
    LazyVerticalGrid(
        state = state,
        columns = GridCells.Adaptive(NiWishListSpecs.CellMinWidth),
        contentPadding = NiWishListSpecs.Padding,
        verticalArrangement = NiWishListSpecs.VerticalSeparation,
        horizontalArrangement = NiWishListSpecs.HorizontalSeparation,
        modifier = modifier
    ) {
        items(
            items = wishes,
            key = { wish -> wish.id }
        ) { wish ->
            NiWishCard(
                onClick = { onItemClick(wish.id) },
                onLongClick = { onItemLongClick(wish.id) },
                imageUrl = wish.imageUrl,
                shared = wish.shared
            )
        }
    }
}