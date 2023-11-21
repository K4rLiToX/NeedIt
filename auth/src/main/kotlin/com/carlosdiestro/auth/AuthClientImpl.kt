package com.carlosdiestro.auth

import android.content.Intent
import android.content.IntentSender
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthCredential
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class AuthClientImpl @Inject constructor(
    private val anonymousAuthClient: AnonymousAuthClient,
    private val googleAuthUiClient: GoogleAuthUiClient,
    private val auth: FirebaseAuth
) : AuthClient {

    override val signedInUser: UserAuth? = auth.currentUser.asUserAuth()

    override suspend fun signInAnonymously(): SignInResult =
        anonymousAuthClient.signIn()

    override suspend fun requestGoogleIntent(): IntentSender? =
        googleAuthUiClient.requestIntent()

    override suspend fun signInWithGoogle(intent: Intent): SignInResult =
        googleAuthUiClient.signIn(intent)

    override suspend fun linkAccount(intent: Intent): SignInResult {
        val credential = googleAuthUiClient.getGoogleAuthCredentials(intent) as GoogleAuthCredential
        return try {
            val user = auth.currentUser?.linkWithCredential(credential)?.await().createUserAuth()
            SignInResult(
                data = user,
                errorMessage = null,
                isNewUser = false
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message,
                isNewUser = false
            )
        }
    }

    override suspend fun signOut() = kotlin.run {
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