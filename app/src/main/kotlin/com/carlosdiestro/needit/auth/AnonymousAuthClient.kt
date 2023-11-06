package com.carlosdiestro.needit.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class AnonymousAuthClient @Inject constructor(
    private val auth: FirebaseAuth
) {
    suspend fun signIn(): SignInResult {
        return try {
            val user = auth.signInAnonymously().await().user
            SignInResult(
                data = user?.run {
                    UserAuth(
                        userId = uid,
                        username = "Anonymous",
                        email = "",
                        profilePictureUrl = "",
                        isAnonymous = true
                    )
                },
                errorMessage = null,
                isNewUser = true
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

    fun signOut() {
        try {
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }
}