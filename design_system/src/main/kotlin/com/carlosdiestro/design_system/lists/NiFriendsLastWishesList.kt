package com.carlosdiestro.design_system.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.carlosdiestro.design_system.cards.FriendPlo
import com.carlosdiestro.design_system.cards.FriendWishPlo
import com.carlosdiestro.design_system.cards.NiFriendLastWishesCard
import com.carlosdiestro.design_system.theme.dimensions

data class FriendsWithWishesPlo(
    val friend: FriendPlo,
    val wishes: List<FriendWishPlo>
)

@Composable
fun NiFriendsLastWishesList(
    modifier: Modifier = Modifier,
    onWishClick: (String) -> Unit,
    onWishLongClick: (String) -> Unit,
    onFriendClick: (String) -> Unit,
    lazyListState: LazyListState,
    friendsWithWishes: List<FriendsWithWishesPlo>
) {
    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingL),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(
            vertical = MaterialTheme.dimensions.spacingL
        ),
        modifier = modifier
    ) {
        items(
            items = friendsWithWishes,
            key = { it.friend.id }
        ) { friendsWithWishes ->
            NiFriendLastWishesCard(
                friend = friendsWithWishes.friend,
                wishes = friendsWithWishes.wishes,
                onWishClick = onWishClick,
                onWishLongClick = onWishLongClick,
                onFriendClick = onFriendClick
            )
        }
    }
}