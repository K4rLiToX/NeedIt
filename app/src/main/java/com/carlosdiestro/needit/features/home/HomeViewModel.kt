package com.carlosdiestro.needit.features.home

import androidx.lifecycle.ViewModel
import com.carlosdiestro.needit.core.design_system.components.cards.WishPLO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class HomeViewModel : ViewModel() {
    private var _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
}

data class HomeState(
    val wishes: List<WishPLO> = emptyList(),
) {
    val showEmptyScreen: Boolean = wishes.isEmpty()
}