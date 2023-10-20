package com.carlosdiestro.needit.features.friends

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun FriendsRoute(
    onFriendClick: (String) -> Unit,
    viewModel: FriendsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    FriendsScreen(
        state = state
    )
}

@Composable
private fun FriendsScreen(
    state: FriendsState
) {

}