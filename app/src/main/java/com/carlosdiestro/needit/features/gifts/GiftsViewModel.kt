package com.carlosdiestro.needit.features.gifts

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class GiftsViewModel : ViewModel() {
    private var _state: MutableStateFlow<GiftsState> = MutableStateFlow(GiftsState())
    val state = _state.asStateFlow()
}

data class GiftsState(
    val gifts: List<String> = emptyList()
) {
    val showEmptyScreen: Boolean = gifts.isEmpty()
}

