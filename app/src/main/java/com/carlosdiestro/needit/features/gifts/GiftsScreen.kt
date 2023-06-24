package com.carlosdiestro.needit.features.gifts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.container.NeedItScreenContainer
import com.carlosdiestro.needit.core.design_system.components.navigation.NeedItTopAppBar

@Composable
fun GiftsRoute(
    viewModel: GiftsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    GiftsScreen(
        state = state
    )
}

@Composable
private fun GiftsScreen(
    state: GiftsState
) {
    NeedItScreenContainer(
        topAppBar = {
            NeedItTopAppBar(
                title = stringResource(id = R.string.gifts_title)
            )
        }
    ) {

    }
}