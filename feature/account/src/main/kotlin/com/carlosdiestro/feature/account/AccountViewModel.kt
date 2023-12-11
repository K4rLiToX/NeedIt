package com.carlosdiestro.feature.account

import android.content.Intent
import android.content.IntentSender
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.auth.UserAuth
import com.carlosdiestro.user.domain.User
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
    private val accountService: AccountService
) : ViewModel() {

    private var _googleSignInIntentState: MutableStateFlow<IntentSender?> = MutableStateFlow(null)
    val googleSignInIntentState = _googleSignInIntentState.asStateFlow()

    private var _signInErrorState: MutableStateFlow<String?> = MutableStateFlow(null)
    val signInErrorState = _signInErrorState.asStateFlow()

    val state: StateFlow<AccountDataState> = accountService.currentUser
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
            val intent = accountService.requestGoogleIntent()
            _googleSignInIntentState.update { intent }
        }
    }

    fun linkAccount(intent: Intent) {
        viewModelScope.launch {
            val signInResult = accountService.linkAccount(intent)
            _signInErrorState.update { signInResult.errorMessage }
            if (signInResult.data != null) accountService.upsertUser(signInResult.data!!.asDomain())
        }
    }

    fun signOut() {
        viewModelScope.launch {
            accountService.signOut()
        }
    }
}

fun UserAuth.asDomain(): User = User(
    id = userId,
    username = username,
    email = email,
    profilePictureUrl = profilePictureUrl.orEmpty(),
    isAnonymous = isAnonymous
)