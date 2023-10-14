package com.carlosdiestro.needit.features.sign_in

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.carlosdiestro.needit.auth.UserAuth

data class SignInDataState(
    val userAuth: UserAuth? = null,
    val signInError: String? = null
)

@Composable
fun rememberSignInUiState(
    context: Context = LocalContext.current,
    lifecycleScope: LifecycleCoroutineScope = LocalLifecycleOwner.current.lifecycleScope
): SignInUiState {
    return rememberSaveable {
        SignInUiState(
            context = context,
            lifecycleScope = lifecycleScope
        )
    }
}

@Stable
class SignInUiState(
    val context: Context,
    val lifecycleScope: LifecycleCoroutineScope
)