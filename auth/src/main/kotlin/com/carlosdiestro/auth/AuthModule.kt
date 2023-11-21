package com.carlosdiestro.auth

import android.content.Context
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AuthModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideAnonymousAuthClient(auth: FirebaseAuth): AnonymousAuthClient =
        AnonymousAuthClient(auth)

    @Provides
    @Singleton
    fun provideGoogleAuthUiClient(
        @ApplicationContext context: Context,
        auth: FirebaseAuth
    ): GoogleAuthUiClient = GoogleAuthUiClient(
        oneTapClient = Identity.getSignInClient(context),
        auth = auth,
        webClientId = context.getString(R.string.web_client_id)
    )

    @Provides
    @Singleton
    fun provideAuthClient(
        anonymousAuthClient: AnonymousAuthClient,
        googleAuthUiClient: GoogleAuthUiClient,
        auth: FirebaseAuth
    ): AuthClient = AuthClientImpl(
        anonymousAuthClient = anonymousAuthClient,
        googleAuthUiClient = googleAuthUiClient,
        auth = auth
    )
}