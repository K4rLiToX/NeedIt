package com.carlosdiestro.remote_database

import com.carlosdiestro.remote_database.firestore.friends.FriendRequestsCollection
import com.carlosdiestro.remote_database.firestore.friends.FriendsCollection
import com.carlosdiestro.remote_database.firestore.users.UsersCollection
import com.carlosdiestro.remote_database.firestore.wishes.WishesCollection
import com.carlosdiestro.remote_database.storage.ImagesCollection
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
internal object FirebaseModule {

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
    fun provideFriendRequestsCollection(firestore: FirebaseFirestore): FriendRequestsCollection =
        FriendRequestsCollection(firestore.collection(CollectionsPath.friendRequests))

    @Provides
    @Singleton
    fun provideFriendsCollection(firestore: FirebaseFirestore): FriendsCollection =
        FriendsCollection(firestore.collection(CollectionsPath.users))

    @Provides
    @Singleton
    fun provideImageCollection(
        storage: FirebaseStorage
    ): ImagesCollection =
        ImagesCollection(storage)
}