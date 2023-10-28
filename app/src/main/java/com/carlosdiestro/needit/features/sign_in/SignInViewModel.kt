package com.carlosdiestro.needit.features.sign_in

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.auth.AuthClient
import com.carlosdiestro.needit.auth.SignInResult
import com.carlosdiestro.needit.auth.UserAuth
import com.carlosdiestro.needit.core.mappers.asDomain
import com.carlosdiestro.needit.domain.users.usecases.UpsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val upsertUser: UpsertUserUseCase,
    private val authClient: AuthClient
) : ViewModel() {

    private var _state: MutableStateFlow<SignInDataState> = MutableStateFlow(SignInDataState())
    val state = _state.asStateFlow()

    val signedInUser = authClient.signedInUser

    fun signInAnonymously() {
        viewModelScope.launch {
            val signInResult = authClient.signInAnonymously()
            updateState(signInResult)
        }
    }

    fun signInWithGoogle(intent: Intent) {
        viewModelScope.launch {
            val signInResult = authClient.signInWithGoogle(intent)
            updateState(signInResult)
        }
    }

    fun requestGoogleSignInIntent() {
        viewModelScope.launch {
            val intent = authClient.requestGoogleIntent()
            _state.update {
                it.copy(
                    googleIntent = intent
                )
            }
        }
    }

    fun createNewUser(userAuth: UserAuth) {
        viewModelScope.launch {
            upsertUser(userAuth.asDomain())
        }
    }

    fun resetState() {
        _state.update { SignInDataState() }
    }

    private fun updateState(result: SignInResult) {
        _state.update {
            it.copy(
                userAuth = result.data,
                signInError = result.errorMessage
            )
        }
    }
}