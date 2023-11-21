package com.carlosdiestro.design_system.lists

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.carlosdiestro.design_system.extensions.conditional
import com.carlosdiestro.design_system.theme.dimensions
import com.carlosdiestro.design_system.theme.shape

@Composable
fun NiCategoryScrollable(
    modifier: Modifier = Modifier,
    categories: List<WishCategoryPlo>,
    selectedIndex: Int,
    listState: LazyListState,
    snapBehavior: FlingBehavior,
    screenWidth: Dp,
    onCategoryClick: (Int) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingM),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = screenWidth / 2),
        state = listState,
        flingBehavior = snapBehavior,
        modifier = modifier
    ) {
        items(
            items = categories,
            key = { category -> category.ordinal }
        ) { category ->
            val textBackGroundColor by animateColorAsState(
                targetValue = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),
                label = "Text background color"
            )
            Text(
                text = stringResource(id = category.labelId),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .clip(MaterialTheme.shape.full)
                    .clickable {
                        onCategoryClick(category.ordinal)
                    }
                    .conditional(
                        condition = selectedIndex == category.toIntValue(),
                        ifTrue = {
                            drawBehind {
                                drawRect(
                                    textBackGroundColor
                                )
                            }
                        }
                    )
                    .padding(
                        horizontal = MaterialTheme.dimensions.spacingS,
                        vertical = MaterialTheme.dimensions.spacingXS
                    )
            )
        }
    }
}