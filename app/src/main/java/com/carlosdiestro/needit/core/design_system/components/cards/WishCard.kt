package com.carlosdiestro.needit.core.design_system.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.core.design_system.components.buttons.IconButtonSpecs
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItFilledIconButton
import com.carlosdiestro.needit.core.design_system.components.images.NeedItImageContainer
import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icons

class SimpleWishPLO(
    val id: Long,
    val title: String,
    val imageUrl: String,
    val isShared: Boolean,
    val category: WishCategory
)

@Composable
fun WishCard(
    title: String,
    imageUrl: String,
    isShared: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    NeedItImageContainer(
        imageUrl = imageUrl,
        contentDescription = title,
        width = 172.dp,
        height = 220.dp,
        onClick = onClick,
        onLongClick = onLongClick,
        cardContent = {
            WishCardContent(
                isShared = isShared
            )
        }
    )
}

@Composable
private fun WishCardContent(
    isShared: Boolean
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        IconSection(isShared = isShared)
    }
}

@Composable
private fun IconSection(
    isShared: Boolean
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimensions.spacingS)
    ) {
        if (isShared) {
            NeedItFilledIconButton(
                icon = MaterialTheme.icons.Share,
                containerSize = IconButtonSpecs.containerSmall,
                iconSize = IconButtonSpecs.iconExtraSmall,
                onClick = {},
            )
        }
    }
}

@Preview
@Composable
private fun WishCardPreview() {
    val wish1 = SimpleWishPLO(
        id = 0,
        imageUrl = "",
        title = "Chanel",
        isShared = false,
        category = WishCategory.Clothes
    )
    val wish2 = SimpleWishPLO(
        id = 1,
        imageUrl = "",
        title = "Chanel",
        isShared = true,
        category = WishCategory.Books
    )
    NeedItTheme {
        Column {
            WishCard(
                title = wish1.title,
                imageUrl = wish1.imageUrl,
                isShared = wish1.isShared,
                onClick = {},
                onLongClick = {}
            )
            WishCard(
                title = wish2.title,
                imageUrl = wish2.imageUrl,
                isShared = wish2.isShared,
                onClick = {},
                onLongClick = {}
            )
        }
    }
}