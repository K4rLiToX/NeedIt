package com.carlosdiestro.needit.features.sign_in

import android.content.IntentSender
import com.carlosdiestro.needit.auth.UserAuth

data class SignInDataState(
    val userAuth: UserAuth? = null,
    val signInError: String? = null,
    val googleIntent: IntentSender? = null
)