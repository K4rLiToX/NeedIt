package com.carlosdiestro.feature.friends

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.design_system.lists.NiFriendsLastWishesList

@Composable
internal fun FriendsRoute(
    onFriendClick: (String) -> Unit,
    viewModel: FriendsViewModel = hiltViewModel()
) {
    val dataState by viewModel.state.collectAsStateWithLifecycle()
    FriendsScreen(
        state = dataState,
        onWishClick = viewModel::onWishClick,
        onWishLongClick = viewModel::onWishLongClick,
        onFriendClick = onFriendClick,
    )
}

@Composable
private fun FriendsScreen(
    state: FriendsDataState,
    onWishClick: (String) -> Unit,
    onWishLongClick: (String) -> Unit,
    onFriendClick: (String) -> Unit
) {
    NiFriendsLastWishesList(
        onWishClick = onWishClick,
        onWishLongClick = onWishLongClick,
        onFriendClick = onFriendClick,
        lazyListState = rememberLazyListState(),
        friendsWithWishes = state.friendsWithWishes,
        modifier = Modifier
            .fillMaxSize()
    )
}