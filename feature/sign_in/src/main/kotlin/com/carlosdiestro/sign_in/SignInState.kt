package com.carlosdiestro.sign_in

import android.content.IntentSender
import com.carlosdiestro.auth.UserAuth

internal data class SignInDataState(
    val userAuth: UserAuth? = null,
    val isNewUser: Boolean = true,
    val signInError: String? = null,
    val googleIntent: IntentSender? = null
)