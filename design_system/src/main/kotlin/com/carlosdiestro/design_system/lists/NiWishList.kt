package com.carlosdiestro.design_system.lists

import androidx.annotation.StringRes
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.carlosdiestro.design_system.R
import com.carlosdiestro.design_system.cards.NiWishCard

data class HomeWishPlo(
    val id: String,
    val imageUrl: String,
    val shared: Boolean,
    val category: WishCategoryPlo
)

enum class WishCategoryPlo(@StringRes val labelId: Int) {
    Clothes(R.string.item_category_clothes),
    Footwear(R.string.item_category_footwear),
    Accessories(R.string.item_category_accessories),
    Grooming(R.string.item_category_grooming),
    Books(R.string.item_category_books),
    Tech(R.string.item_category_tech),
    Other(R.string.item_category_other);

    fun toIntValue(): Int = this.ordinal
}

fun Int.toWishCategoryPlo(): WishCategoryPlo = WishCategoryPlo.entries[this]

@Composable
fun NiHomeWishList(
    onItemClick: (String) -> Unit,
    onItemLongClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    lazyGridState: LazyGridState,
    wishes: List<HomeWishPlo>,
    selectedWishId: String?
) {
    LazyVerticalGrid(
        state = lazyGridState,
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
                shared = wish.shared,
                selected = wish.id == selectedWishId
            )
        }
    }
}