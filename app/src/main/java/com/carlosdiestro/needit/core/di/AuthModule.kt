package com.carlosdiestro.needit.core.di

import android.content.Context
import com.carlosdiestro.needit.auth.GoogleAuthUiClient
import com.google.android.gms.auth.api.identity.Identity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideGoogleAuthUiClient(
        @ApplicationContext context: Context
    ): GoogleAuthUiClient = GoogleAuthUiClient(
        context = context,
        oneTapClient = Identity.getSignInClient(context)
    )

}