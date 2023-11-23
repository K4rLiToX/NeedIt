package com.carlosdiestro.feature.friends

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class FriendsViewModel @Inject constructor() : ViewModel() {
    private var _state: MutableStateFlow<FriendsState> = MutableStateFlow(FriendsState())
    val state = _state.asStateFlow()
}

internal data class FriendsState(
    val friends: List<String> = emptyList()
) {
    val showEmptyScreen: Boolean = friends.isEmpty()
}