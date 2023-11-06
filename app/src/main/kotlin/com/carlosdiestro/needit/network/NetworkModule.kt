package com.carlosdiestro.needit.network

import com.carlosdiestro.needit.core.di.IoDispatcher
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
import kotlinx.coroutines.CoroutineDispatcher
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
    fun provideUsersCollection(firestore: FirebaseFirestore): UsersCollection =
        UsersCollection(firestore.collection(CollectionsPath.users))

    @Provides
    @Singleton
    fun provideUsersWishesCollection(
        firestore: FirebaseFirestore,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): WishesCollection = WishesCollection(
        usersCollection = firestore.collection(CollectionsPath.users),
        ioDispatcher = dispatcher
    )

    @Provides
    @Singleton
    fun provideImageCollection(storage: FirebaseStorage): ImagesCollection =
        ImagesCollection(storage)
}