package com.carlosdiestro.feature.friends

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun FriendsRoute(
    onFriendClick: (String) -> Unit,
    viewModel: FriendsViewModel = hiltViewModel()
) {
    FriendsScreen(
        state = FriendsDataState(),
        onFriendClick = onFriendClick,
    )
}

@Composable
private fun FriendsScreen(
    state: FriendsDataState,
    onFriendClick: (String) -> Unit
) {

}