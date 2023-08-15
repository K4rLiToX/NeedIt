package com.carlosdiestro.needit.features.sign_in

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.carlosdiestro.needit.auth.GoogleAuthUiClient
import com.carlosdiestro.needit.auth.SignInResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    val googleAuthUiClient: GoogleAuthUiClient
) : ViewModel() {

    private var _state: MutableStateFlow<SignInUiState> = MutableStateFlow(SignInUiState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun resetState() {
        _state.update { SignInUiState() }
    }
}

data class SignInUiState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)