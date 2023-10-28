package com.carlosdiestro.needit.auth

import android.content.Intent
import android.content.IntentSender
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthCredential
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthClient @Inject constructor(
    private val anonymousAuthClient: AnonymousAuthClient,
    private val googleAuthUiClient: GoogleAuthUiClient,
    private val auth: FirebaseAuth
) {

    val signedInUser: UserAuth? = auth.currentUser.asUserAuth()

    suspend fun signInAnonymously(): SignInResult =
        anonymousAuthClient.signIn()

    suspend fun requestGoogleIntent(): IntentSender? =
        googleAuthUiClient.requestIntent()

    suspend fun signInWithGoogle(intent: Intent): SignInResult =
        googleAuthUiClient.signIn(intent)

    suspend fun linkAccount(intent: Intent): SignInResult {
        val credential = googleAuthUiClient.getGoogleAuthCredentials(intent) as GoogleAuthCredential
        val user = auth.currentUser?.linkWithCredential(credential)?.await().createUserAuth()
        return try {
            SignInResult(
                data = user,
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    suspend fun signOut() = kotlin.run {
        anonymousAuthClient.signOut()
        googleAuthUiClient.signOut()
    }
}

private fun FirebaseUser?.asUserAuth(): UserAuth? = this?.run {
    UserAuth(
        userId = uid,
        username = displayName ?: "Anonymous",
        email = email ?: "",
        profilePictureUrl = photoUrl?.toString() ?: "",
        isAnonymous = isAnonymous
    )
}

private fun AuthResult?.createUserAuth(): UserAuth? = this?.run {
    val userId = user?.uid ?: ""
    val email = user?.email ?: ""
    val isAnonymous = user?.isAnonymous ?: true
    val username = additionalUserInfo?.profile?.get("name") as String
    val profilePictureUrl = additionalUserInfo?.profile?.get("picture") as String
    UserAuth(
        userId = userId,
        username = username,
        email = email,
        profilePictureUrl = profilePictureUrl,
        isAnonymous = isAnonymous
    )
}