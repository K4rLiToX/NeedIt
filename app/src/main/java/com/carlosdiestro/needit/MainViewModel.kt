package com.carlosdiestro.needit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.domain.users.usecases.GetUserInfoUseCase
import com.carlosdiestro.needit.domain.users.usecases.IsUserGuestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    isUserGuestUseCase: IsUserGuestUseCase,
    getUserInfo: GetUserInfoUseCase
) : ViewModel() {

    val state: StateFlow<MainState> = combine(
        isUserGuestUseCase(),
        getUserInfo()
    ) { isUserGuest, userInfo ->
        MainState(
            profilePictureUrl = userInfo.profilePictureUrl,
            isUserGuest = isUserGuest
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainState()
        )
}

data class MainState(
    val profilePictureUrl: String = "",
    val isUserGuest: Boolean = true
)