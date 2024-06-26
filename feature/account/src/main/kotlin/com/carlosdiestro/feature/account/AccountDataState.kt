package com.carlosdiestro.feature.account

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

internal sealed interface AccountDataState {
    data object Loading : AccountDataState
    data class Success(val account: Account) : AccountDataState
}

internal data class Account(
    val username: String,
    val email: String,
    val profilePictureUrl: String,
    val isAnonymous: Boolean,
    val birthday: String,
    val currency: String
)

@Composable
internal fun rememberAccountDialogState(
    context: Context = LocalContext.current
): AccountDialogUiState {
    return remember {
        AccountDialogUiState(
            context = context
        )
    }
}

@Stable
internal class AccountDialogUiState(
    val context: Context
) {

    var shouldShowAccountExtras by mutableStateOf(false)
        private set

    fun setShowAccountExtra(show: Boolean) {
        shouldShowAccountExtras = show
    }
}