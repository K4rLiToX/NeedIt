package com.carlosdiestro.needit.features.profile

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItFilledIconButton
import com.carlosdiestro.needit.core.design_system.components.container.NeedItScreenContainer
import com.carlosdiestro.needit.core.design_system.components.navigation.NeedItTopAppBar
import com.carlosdiestro.needit.core.design_system.theme.icons

@Composable
fun ProfileRoute(
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ProfileScreen(
        state = state,
        onItemClick = onItemClick,
        onItemLongClick = onItemLongClick
    )
}

@Composable
private fun ProfileScreen(
    state: ProfileState,
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit
) {
    NeedItScreenContainer(
        topAppBar = {
            NeedItTopAppBar(
                actions = {
                    NeedItFilledIconButton(
                        icon = MaterialTheme.icons.Sort,
                        onClick = {}
                    )
                }
            )
        }
    ) {

    }
}