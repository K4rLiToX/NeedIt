package com.carlosdiestro.needit.features.sign_in

import android.app.Activity
import android.content.Intent
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.auth.UserAuth
import com.carlosdiestro.needit.core.design_system.components.buttons.NiButtonSpecs
import com.carlosdiestro.needit.core.design_system.components.buttons.NiFilledButton
import com.carlosdiestro.needit.core.design_system.components.buttons.NiOutlinedButton
import com.carlosdiestro.needit.core.design_system.theme.dimensions

@Composable
internal fun SignInRoute(
    onSignInSuccessful: () -> Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val dataState by viewModel.state.collectAsStateWithLifecycle()

    SignInScreen(
        dataState = dataState,
        signInAnonymously = viewModel::signInAnonymously,
        requestGoogleSignInIntent = viewModel::requestGoogleSignInIntent,
        signInWithGoogle = viewModel::signInWithGoogle,
        resetState = viewModel::resetState,
        onSignInSuccessful = onSignInSuccessful,
        createUser = viewModel::createNewUser
    )
}

@Composable
private fun SignInScreen(
    dataState: SignInDataState,
    signInAnonymously: () -> Unit,
    requestGoogleSignInIntent: () -> Unit,
    signInWithGoogle: (Intent) -> Unit,
    resetState: () -> Unit,
    onSignInSuccessful: () -> Unit,
    createUser: (UserAuth) -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                signInWithGoogle(result.data ?: return@rememberLauncherForActivityResult)
            }
        }
    )
    val context = LocalContext.current

    LaunchedEffect(key1 = dataState.signInError) {
        dataState.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    LaunchedEffect(key1 = dataState.googleIntent) {
        if (dataState.googleIntent != null) {
            launcher.launch(
                IntentSenderRequest.Builder(
                    intentSender = dataState.googleIntent
                ).build()
            )
        }
    }

    LaunchedEffect(key1 = dataState.userAuth) {
        if (dataState.userAuth != null) {
            if (dataState.isNewUser) createUser(dataState.userAuth)
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
        NiFilledButton(
            labelId = R.string.button_login,
            onClick = requestGoogleSignInIntent,
            height = NiButtonSpecs.Height.Large,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingM))
        NiOutlinedButton(
            labelId = R.string.button_continue_as_guest,
            onClick = signInAnonymously,
            height = NiButtonSpecs.Height.Large,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}