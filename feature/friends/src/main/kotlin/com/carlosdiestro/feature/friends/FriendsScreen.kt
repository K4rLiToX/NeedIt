package com.carlosdiestro.feature.friends

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.design_system.i18n.Localization
import com.carlosdiestro.design_system.lists.NiFriendsLastWishesList
import com.carlosdiestro.design_system.theme.dimensions

@Composable
internal fun FriendsRoute(
    onFriendClick: (String) -> Unit,
    viewModel: FriendsViewModel = hiltViewModel()
) {
    val dataState by viewModel.state.collectAsStateWithLifecycle()
    when (dataState.isAnonymous) {
        true -> FriendGuestScreen(Modifier.fillMaxSize())
        else -> {
            if (dataState.showEmptyScreen) FriendEmptyScreen(Modifier.fillMaxSize())
            else FriendsScreen(
                state = dataState,
                onWishClick = viewModel::onWishClick,
                onWishLongClick = viewModel::onWishLongClick,
                onFriendClick = onFriendClick
            )
        }
    }
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

@Composable
private fun FriendGuestScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = Localization.Friends.Guest,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(MaterialTheme.dimensions.spacingXXL)
        )
    }
}

@Composable
private fun FriendEmptyScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = Localization.Friends.Empty,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(MaterialTheme.dimensions.spacingXXL)
        )
    }
}