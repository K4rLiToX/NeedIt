package com.carlosdiestro.needit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.auth.AuthClient
import com.carlosdiestro.needit.domain.users.usecases.GetSignedInUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getSignedInUser: GetSignedInUserUseCase,
    private val authClient: AuthClient
) : ViewModel() {

    val state: StateFlow<MainState> = getSignedInUser().map { user ->
        MainState(
            profilePictureUrl = user.profilePictureUrl,
            isUserAnonymous = user.isAnonymous,
            isSignedIn = authClient.signedInUser != null
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MainState(isSignedIn = authClient.signedInUser != null)
    )
}

data class MainState(
    val isSignedIn: Boolean,
    val profilePictureUrl: String = "",
    val isUserAnonymous: Boolean = true
)