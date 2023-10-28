package com.carlosdiestro.needit

import android.content.Intent
import android.content.IntentSender
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.auth.AuthClient
import com.carlosdiestro.needit.core.mappers.asDomain
import com.carlosdiestro.needit.domain.users.usecases.GetSignedInUserUseCase
import com.carlosdiestro.needit.domain.users.usecases.UpsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getSignedInUser: GetSignedInUserUseCase,
    private val upsertUser: UpsertUserUseCase,
    private val authClient: AuthClient
) : ViewModel() {

    private val _googleIntentState: MutableStateFlow<IntentSender?> = MutableStateFlow(null)
    private val _signInErrorState: MutableStateFlow<String?> = MutableStateFlow(null)

    val state: StateFlow<MainState> = combine(
        getSignedInUser(),
        _googleIntentState,
        _signInErrorState
    ) { user, googleIntent, signInError ->
        MainState(
            username = user.username,
            email = user.email,
            profilePictureUrl = user.profilePictureUrl,
            isUserAnonymous = user.isAnonymous,
            signInError = signInError,
            isSignedIn = authClient.signedInUser != null,
            googleIntent = googleIntent
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MainState(isSignedIn = authClient.signedInUser != null)
    )

    fun requestGoogleSignInIntent() {
        viewModelScope.launch {
            val intent = authClient.requestGoogleIntent()
            _googleIntentState.update { intent }
        }
    }

    fun linkAccount(intent: Intent) {
        viewModelScope.launch {
            val signInResult = authClient.linkAccount(intent)
            _signInErrorState.update {
                signInResult.errorMessage
            }
            if (signInResult.data != null) upsertUser(signInResult.data.asDomain())
        }
    }
}

data class MainState(
    val username: String = "",
    val email: String = "",
    val profilePictureUrl: String = "",
    val isUserAnonymous: Boolean = true,
    val signInError: String? = null,
    val isSignedIn: Boolean,
    val googleIntent: IntentSender? = null,
)