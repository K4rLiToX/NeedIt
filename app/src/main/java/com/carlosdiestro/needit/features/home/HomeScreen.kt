package com.carlosdiestro.needit.features.home

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItFilledIconButton
import com.carlosdiestro.needit.core.design_system.components.container.NeedItScreenContainer
import com.carlosdiestro.needit.core.design_system.components.navigation.NeedItTopAppBar
import com.carlosdiestro.needit.core.design_system.theme.icons

@Composable
fun HomeRoute(
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(
        state = state,
        onItemClick = onItemClick,
        onItemLongClick = onItemLongClick
    )
}

@Composable
private fun HomeScreen(
    state: HomeState,
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit,
) {
    NeedItScreenContainer(
        topAppBar = {
            NeedItTopAppBar(
                title = stringResource(id = R.string.home_title),
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