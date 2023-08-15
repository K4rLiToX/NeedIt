package com.carlosdiestro.needit.network

import com.carlosdiestro.needit.network.CollectionsPath
import com.carlosdiestro.needit.network.collections.UsersCollection
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideUsersCollection(firestore: FirebaseFirestore): UsersCollection =
        UsersCollection(firestore.collection(CollectionsPath.users))
}