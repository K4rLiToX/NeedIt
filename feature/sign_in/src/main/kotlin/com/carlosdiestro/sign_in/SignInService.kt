package com.carlosdiestro.sign_in

import android.content.Intent
import android.content.IntentSender
import com.carlosdiestro.auth.AuthClient
import com.carlosdiestro.auth.SignInResult
import com.carlosdiestro.user.domain.User
import com.carlosdiestro.user.domain.UserRepository
import javax.inject.Inject

class SignInService @Inject constructor(
    private val userRepository: UserRepository,
    private val authClient: AuthClient
) {

    suspend fun signInAnonymously(): SignInResult = authClient.signInAnonymously()

    suspend fun signInWithGoogle(intent: Intent): SignInResult = authClient.signInWithGoogle(intent)

    suspend fun requestGoogleIntent(): IntentSender? = authClient.requestGoogleIntent()

    suspend fun create(user: User) = userRepository.upsert(user)
}