package com.carlosdiestro.needit.network

import com.carlosdiestro.needit.network.images.ImagesCollection
import com.carlosdiestro.needit.network.users.UsersCollection
import com.carlosdiestro.needit.network.wishes.WishesCollection
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
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideStorage(): FirebaseStorage = Firebase.storage

    @Provides
    @Singleton
    fun provideUsersCollection(firestore: FirebaseFirestore): UsersCollection =
        UsersCollection(firestore.collection(CollectionsPath.users))

    @Provides
    @Singleton
    fun provideUsersWishesCollection(firestore: FirebaseFirestore): WishesCollection =
        WishesCollection(firestore.collection(CollectionsPath.users))

    @Provides
    @Singleton
    fun provideImageCollection(
        storage: FirebaseStorage
    ): ImagesCollection =
        ImagesCollection(storage)
}