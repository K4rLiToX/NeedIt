package com.carlosdiestro.auth

import android.content.Intent
import android.content.IntentSender

interface AuthClient {
    val signedInUser: UserAuth?
    suspend fun signInAnonymously(): SignInResult
    suspend fun requestGoogleIntent(): IntentSender?
    suspend fun signInWithGoogle(intent: Intent): SignInResult
    suspend fun linkAccount(intent: Intent): SignInResult
    suspend fun signOut()
}