package com.carlosdiestro.design_system.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carlosdiestro.design_system.avatars.NiAvatar
import com.carlosdiestro.design_system.icon_buttons.NiIconButton
import com.carlosdiestro.design_system.icon_buttons.NiIconButtonSpecs
import com.carlosdiestro.design_system.theme.dimensions
import com.carlosdiestro.design_system.theme.icons

data class FriendWishPlo(
    val id: String,
    val imageUrl: String,
    val title: String,
    val price: String,
    val isReserved: Boolean
)

data class FriendPlo(
    val id: String,
    val username: String,
    val profilePictureUrl: String
)

@Composable
fun NiFriendLastWishesCard(
    modifier: Modifier = Modifier,
    friend: FriendPlo,
    wishes: List<FriendWishPlo>,
    onWishClick: (String) -> Unit,
    onWishLongClick: (String) -> Unit,
    onFriendClick: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingM),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Header(
            profilePictureUrl = friend.profilePictureUrl,
            username = friend.username,
            onClick = { onFriendClick(friend.id) }
        )
        WishesCarousel(
            wishes = wishes,
            onWishClick = onWishClick,
            onWishLongClick = onWishLongClick
        )
    }
}

@Composable
private fun Header(
    profilePictureUrl: String,
    username: String,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = MaterialTheme.dimensions.spacingM)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingXS),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NiAvatar(
                imageUrl = profilePictureUrl
            )
            Text(
                text = username,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        NiIconButton(
            icon = MaterialTheme.icons.ArrowRight,
            colors = NiIconButtonSpecs.Color.secondary(),
            onClick = onClick
        )
    }
}

@Composable
private fun WishesCarousel(
    wishes: List<FriendWishPlo>,
    onWishClick: (String) -> Unit,
    onWishLongClick: (String) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingXS),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = MaterialTheme.dimensions.spacingM)
    ) {
        items(
            items = wishes,
            key = { it.id }
        ) { wish ->
            NiWishCard(
                onClick = { onWishClick(wish.id) },
                onLongClick = { onWishLongClick(wish.id) },
                height = 200.dp,
                width = 200.dp,
                imageUrl = wish.imageUrl,
                title = wish.title,
                price = wish.price,
                reserved = wish.isReserved
            )
        }
    }
}