package com.carlosdiestro.needit.features.account

import android.content.Intent
import android.content.IntentSender
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.auth.AuthClient
import com.carlosdiestro.needit.core.mappers.asDomain
import com.carlosdiestro.needit.domain.users.usecases.GetSignedInUserUseCase
import com.carlosdiestro.needit.domain.users.usecases.UpsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AccountViewModel @Inject constructor(
    private val authClient: AuthClient,
    private val upsertUser: UpsertUserUseCase,
    getSignedInUser: GetSignedInUserUseCase
) : ViewModel() {

    private var _googleSignInIntentState: MutableStateFlow<IntentSender?> = MutableStateFlow(null)
    val googleSignInIntentState = _googleSignInIntentState.asStateFlow()

    private var _signInErrorState: MutableStateFlow<String?> = MutableStateFlow(null)
    val signInErrorState = _signInErrorState.asStateFlow()

    val state: StateFlow<AccountDataState> = getSignedInUser()
        .map { user ->
            AccountDataState.Success(
                account = Account(
                    username = user.username,
                    email = user.email,
                    profilePictureUrl = user.profilePictureUrl,
                    isAnonymous = user.isAnonymous,
                    birthday = "16/04",
                    currency = "EUR €"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AccountDataState.Loading
        )

    fun requestGoogleSignInIntent() {
        viewModelScope.launch {
            val intent = authClient.requestGoogleIntent()
            _googleSignInIntentState.update { intent }
        }
    }

    fun linkAccount(intent: Intent) {
        viewModelScope.launch {
            val signInResult = authClient.linkAccount(intent)
            _signInErrorState.update { signInResult.errorMessage }
            if (signInResult.data != null) upsertUser(signInResult.data!!.asDomain())
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authClient.signOut()
        }
    }
}