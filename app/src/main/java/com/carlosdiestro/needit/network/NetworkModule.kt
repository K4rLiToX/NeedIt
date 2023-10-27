package com.carlosdiestro.needit.network

import com.carlosdiestro.needit.auth.AuthClient
import com.carlosdiestro.needit.network.collections.ImagesCollection
import com.carlosdiestro.needit.network.collections.UsersCollection
import com.carlosdiestro.needit.network.collections.WishesCollection
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
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
    fun provideStorage(): FirebaseStorage = Firebase.storage

    @Provides
    @Singleton
    fun provideUserId(authClient: AuthClient): String =
        authClient.signedInUser?.userId.orEmpty()

    @Provides
    @Singleton
    fun provideUsersCollection(firestore: FirebaseFirestore): UsersCollection =
        UsersCollection(firestore.collection(CollectionsPath.users))

    @Provides
    @Singleton
    fun provideUsersWishesCollection(
        firestore: FirebaseFirestore,
        userId: String
    ): WishesCollection =
        WishesCollection(
            firestore
                .collection(CollectionsPath.users)
                .document(userId)
                .collection(CollectionsPath.userWishes)
        )

    @Provides
    @Singleton
    fun provideImageCollection(storage: FirebaseStorage): ImagesCollection =
        ImagesCollection(storage)
}