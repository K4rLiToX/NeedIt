package com.carlosdiestro.needit.auth

import android.content.Intent
import android.content.IntentSender
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthClient @Inject constructor(
    private val anonymousAuthClient: AnonymousAuthClient,
    private val googleAuthUiClient: GoogleAuthUiClient,
    auth: FirebaseAuth
) {

    val signedInUser: UserAuth? = auth.currentUser?.run {
        UserAuth(
            userId = uid,
            username = displayName ?: "Anonymous",
            email = email ?: "",
            profilePictureUrl = photoUrl?.toString() ?: "",
            isAnonymous = isAnonymous
        )
    }

    suspend fun signInAnonymously(): SignInResult =
        anonymousAuthClient.signIn()

    suspend fun requestGoogleIntent(): IntentSender? =
        googleAuthUiClient.requestIntent()

    suspend fun signInWithGoogle(intent: Intent): SignInResult =
        googleAuthUiClient.signIn(intent)

    suspend fun signOut() = kotlin.run {
        anonymousAuthClient.signOut()
        googleAuthUiClient.signOut()
    }
}