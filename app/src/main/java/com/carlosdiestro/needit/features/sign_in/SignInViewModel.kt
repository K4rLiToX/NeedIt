package com.carlosdiestro.needit.features.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.auth.GoogleAuthUiClient
import com.carlosdiestro.needit.auth.SignInResult
import com.carlosdiestro.needit.auth.UserAuth
import com.carlosdiestro.needit.core.mappers.asDomain
import com.carlosdiestro.needit.domain.users.usecases.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    val googleAuthUiClient: GoogleAuthUiClient,
    private val signIn: SignInUseCase
) : ViewModel() {

    private var _state: MutableStateFlow<SignInUiState> = MutableStateFlow(SignInUiState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                userAuth = result.data,
                signInError = result.errorMessage
            )
        }
    }

    fun signIn(userAuth: UserAuth) {
        viewModelScope.launch {
            signIn(userAuth.asDomain())
        }
    }

    fun resetState() {
        _state.update { SignInUiState() }
    }
}

data class SignInUiState(
    val userAuth: UserAuth? = null,
    val signInError: String? = null
)