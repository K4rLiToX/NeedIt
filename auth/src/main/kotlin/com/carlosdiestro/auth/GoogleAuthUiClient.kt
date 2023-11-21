package com.carlosdiestro.auth

import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class GoogleAuthUiClient @Inject constructor(
    private val oneTapClient: SignInClient,
    private val auth: FirebaseAuth,
    private val webClientId: String
) {
    suspend fun requestIntent(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            return null
        }
        return result?.pendingIntent?.intentSender
    }

    suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    suspend fun signIn(intent: Intent): SignInResult {
        val googleCredentials = getGoogleAuthCredentials(intent)

        return try {
            val result = auth.signInWithCredential(googleCredentials).await()
            val user = result.user
            val username = result.additionalUserInfo?.profile?.get("name") as String? ?: "User"
            val profilePictureUrl = result.additionalUserInfo?.profile?.get("picture") as String?
                ?: ""
            val isNewUser = result.additionalUserInfo?.isNewUser ?: true
            SignInResult(
                data = user?.run {
                    UserAuth(
                        userId = uid,
                        username = displayName ?: username,
                        email = email ?: "",
                        profilePictureUrl = photoUrl?.toString() ?: profilePictureUrl,
                        isAnonymous = false
                    )
                },
                errorMessage = null,
                isNewUser = isNewUser
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message,
                isNewUser = true
            )
        }
    }

    fun getGoogleAuthCredentials(intent: Intent): AuthCredential {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        return GoogleAuthProvider.getCredential(googleIdToken, null)
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // False -> show all google accounts added on the device
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(webClientId)
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}
