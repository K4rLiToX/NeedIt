package com.carlosdiestro.needit.features.sign_in

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(

) : ViewModel() {

    private var _state: MutableStateFlow<SignInUiState> = MutableStateFlow(SignInUiState())
    val state = _state.asStateFlow()
}

data class SignInUiState(
    val isSignInSuccessful: Boolean = false,
    val errorMessage: String? = null
)