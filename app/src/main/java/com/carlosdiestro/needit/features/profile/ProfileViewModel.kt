package com.carlosdiestro.needit.features.profile

import androidx.lifecycle.ViewModel
import com.carlosdiestro.needit.core.design_system.components.cards.SimpleWishPLO
import com.carlosdiestro.needit.core.extensions.launchCollect
import com.carlosdiestro.needit.domain.users.User
import com.carlosdiestro.needit.domain.users.usecases.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserInfo: GetUserInfoUseCase
) : ViewModel() {
    private var _state: MutableStateFlow<ProfileState> = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    init {
        fetchUserInfo()
    }

    private fun fetchUserInfo() {
        launchCollect(getUserInfo()) { user ->
            _state.update {
                it.copy(
                    userInfo = user
                )
            }
        }
    }
}

data class ProfileState(
    val wishes: List<SimpleWishPLO> = emptyList(),
    val userInfo: User? = null
) {
    val showEmptyScreen: Boolean = wishes.isEmpty()
}