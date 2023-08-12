package com.carlosdiestro.needit.features.sign_in

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItFilledButton
import com.carlosdiestro.needit.core.design_system.theme.button
import com.carlosdiestro.needit.core.design_system.theme.dimensions

@Composable
fun SignInRoute(
    viewModel: SignInViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SignInScreen(
        state = state
    )
}

@Composable
private fun SignInScreen(
    state: SignInUiState
) {
    NeedItFilledButton(
        labelId = R.string.button_login,
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.dimensions.button.largeHeight)
    )
}