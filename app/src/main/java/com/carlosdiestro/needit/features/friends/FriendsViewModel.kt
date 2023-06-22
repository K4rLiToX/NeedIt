package com.carlosdiestro.needit.features.friends

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class FriendsViewModel: ViewModel() {
    private var _state: MutableStateFlow<FriendsState> = MutableStateFlow(FriendsState())
    val state = _state.asStateFlow()
}

data class FriendsState(
    val friends: List<String> = emptyList()
) {
    val showEmptyScreen: Boolean = friends.isEmpty()
}