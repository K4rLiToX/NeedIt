package com.carlosdiestro.needit.features.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.domain.users.usecases.GetSignedInUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    getUserInfo: GetSignedInUserUseCase
) : ViewModel() {

    val state: StateFlow<AccountDataState> = getUserInfo()
        .map { userInfo ->
            AccountDataState(
                profilePictureUrl = userInfo.profilePictureUrl,
                name = userInfo.username,
                email = userInfo.email,
                birthday = "16/04",
                currency = "EUR €"
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AccountDataState()
        )
}