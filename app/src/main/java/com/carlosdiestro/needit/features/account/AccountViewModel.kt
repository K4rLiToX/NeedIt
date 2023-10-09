package com.carlosdiestro.needit.features.account

import androidx.lifecycle.ViewModel
import com.carlosdiestro.needit.core.design_system.components.cards.SimpleWishPLO
import com.carlosdiestro.needit.core.extensions.launchCollect
import com.carlosdiestro.needit.core.mappers.toPLO
import com.carlosdiestro.needit.domain.users.User
import com.carlosdiestro.needit.domain.users.usecases.GetUserInfoUseCase
import com.carlosdiestro.needit.domain.wishes.usecases.GetMyWishesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getUserInfo: GetUserInfoUseCase,
    private val getSharedWishes: GetMyWishesUseCase
) : ViewModel() {

    private var _state: MutableStateFlow<ProfileState> = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    init {
        fetchUserInfo()
        fetchSharedWishes()
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

    private fun fetchSharedWishes() {
        launchCollect(getSharedWishes(onlyShared = true)) { wishes ->
            _state.update {
                it.copy(
                    wishes = wishes.toPLO()
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