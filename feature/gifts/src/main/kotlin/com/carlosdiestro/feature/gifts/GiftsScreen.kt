package com.carlosdiestro.feature.gifts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun GiftsRoute(
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

}