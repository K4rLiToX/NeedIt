package com.carlosdiestro.needit.features.sign_in

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.auth.GoogleAuthUiClient
import com.carlosdiestro.needit.auth.SignInResult
import com.carlosdiestro.needit.auth.UserAuth
import com.carlosdiestro.needit.core.design_system.components.buttons.NiButtonSpecs
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItFilledButton
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItOutlinedButton
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import kotlinx.coroutines.launch

@Composable
fun SignInRoute(
    viewModel: SignInViewModel = hiltViewModel(),
    onSignInSuccessful: () -> Unit,
    onContinueAsGuestClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val googleAuthUiClient = viewModel.googleAuthUiClient

    LaunchedEffect(key1 = Unit) {
        if (googleAuthUiClient.getSignedInUser() != null) onSignInSuccessful()
    }

    SignInScreen(
        state = state,
        googleAuthUiClient = googleAuthUiClient,
        onSignInResult = viewModel::onSignInResult,
        resetState = viewModel::resetState,
        onSignInSuccessful = onSignInSuccessful,
        signIn = viewModel::signIn,
        onContinueAsGuestClick = onContinueAsGuestClick
    )
}

@Composable
private fun SignInScreen(
    state: SignInUiState,
    googleAuthUiClient: GoogleAuthUiClient,
    onSignInResult: (SignInResult) -> Unit,
    resetState: () -> Unit,
    onSignInSuccessful: () -> Unit,
    signIn: (UserAuth) -> Unit,
    onContinueAsGuestClick: () -> Unit
) {
    val context = LocalContext.current
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                lifecycleScope.launch {
                    val signInResult = googleAuthUiClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    onSignInResult(signInResult)
                }
            }
        }
    )

    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    LaunchedEffect(key1 = state.userAuth) {
        if (state.userAuth != null) {
            signIn(state.userAuth)
            onSignInSuccessful()
            resetState()
        }
    }

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.dimensions.spacingM)
            .padding(
                top = MaterialTheme.dimensions.spacingM,
                bottom = MaterialTheme.dimensions.spacingXXL
            )
    ) {
        NeedItFilledButton(
            labelId = R.string.button_login,
            onClick = {
                lifecycleScope.launch {
                    val signInIntentSender = googleAuthUiClient.signIn()
                    launcher.launch(
                        IntentSenderRequest.Builder(
                            intentSender = signInIntentSender ?: return@launch
                        ).build()
                    )
                }
            },
            size = NiButtonSpecs.Large,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingM))
        NeedItOutlinedButton(
            labelId = R.string.button_continue_as_guest,
            onClick = onContinueAsGuestClick,
            size = NiButtonSpecs.Large,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}