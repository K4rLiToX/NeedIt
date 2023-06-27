package com.carlosdiestro.needit.features.profile

import androidx.lifecycle.ViewModel
import com.carlosdiestro.needit.core.design_system.components.cards.SimpleWishPLO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {
    private var _state: MutableStateFlow<ProfileState> = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()
}

data class ProfileState(
    val wishes: List<SimpleWishPLO> = emptyList()
) {
    val showEmptyScreen: Boolean = wishes.isEmpty()
}