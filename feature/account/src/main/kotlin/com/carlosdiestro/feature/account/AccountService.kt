package com.carlosdiestro.feature.account

import android.content.Intent
import android.content.IntentSender
import com.carlosdiestro.auth.AuthClient
import com.carlosdiestro.auth.SignInResult
import com.carlosdiestro.user.domain.User
import com.carlosdiestro.user.domain.UserRepository
import javax.inject.Inject

class AccountService @Inject constructor(
    private val userRepository: UserRepository,
    private val authClient: AuthClient
) {

    val currentUser = userRepository.currentUser

    suspend fun requestGoogleIntent(): IntentSender? = authClient.requestGoogleIntent()

    suspend fun linkAccount(intent: Intent): SignInResult = authClient.linkAccount(intent)

    suspend fun upsertUser(user: User) = userRepository.upsert(user)

    suspend fun signOut() = authClient.signOut()
}