package com.carlosdiestro.needit.features.friends

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.container.NeedItScreenContainer
import com.carlosdiestro.needit.core.design_system.components.navigation.NeedItTopAppBar

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
    NeedItScreenContainer(
        topAppBar = {
            NeedItTopAppBar(
                title = stringResource(id = R.string.friends_title)
            )
        }
    ) {

    }
}