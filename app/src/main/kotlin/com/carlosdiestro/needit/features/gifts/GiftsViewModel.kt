package com.carlosdiestro.needit.features.gifts

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class GiftsViewModel @Inject constructor() : ViewModel() {
    private var _state: MutableStateFlow<GiftsState> = MutableStateFlow(GiftsState())
    val state = _state.asStateFlow()
}

internal data class GiftsState(
    val gifts: List<String> = emptyList()
) {
    val showEmptyScreen: Boolean = gifts.isEmpty()
}

