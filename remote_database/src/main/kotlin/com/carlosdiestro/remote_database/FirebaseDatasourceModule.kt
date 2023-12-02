package com.carlosdiestro.remote_database

import com.carlosdiestro.friend.data.FriendRequestRemoteDatasource
import com.carlosdiestro.friend.data.FriendshipRemoteDatasource
import com.carlosdiestro.remote_database.firestore.friends.FriendRequestRemoteDatasourceImpl
import com.carlosdiestro.remote_database.firestore.friends.FriendshipRemoteDatasourceImpl
import com.carlosdiestro.remote_database.firestore.users.UserRemoteDatasourceImpl
import com.carlosdiestro.remote_database.firestore.wishes.WishRemoteDatasourceImpl
import com.carlosdiestro.remote_database.storage.ImageRemoteDatasourceImpl
import com.carlosdiestro.user.data.datasource.UserRemoteDatasource
import com.carlosdiestro.wish.data.datasource.ImageRemoteDatasource
import com.carlosdiestro.wish.data.datasource.WishRemoteDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface FirebaseDatasourceModule {

    @Singleton
    @Binds
    fun bindUserRemoteDatasource(impl: UserRemoteDatasourceImpl): UserRemoteDatasource

    @Singleton
    @Binds
    fun bindWishRemoteDatasource(impl: WishRemoteDatasourceImpl): WishRemoteDatasource

    @Singleton
    @Binds
    fun bindFriendRequestRemoteDatasource(impl: FriendRequestRemoteDatasourceImpl): FriendRequestRemoteDatasource

    @Singleton
    @Binds
    fun bindFriendshipRemoteDatasource(impl: FriendshipRemoteDatasourceImpl): FriendshipRemoteDatasource

    @Singleton
    @Binds
    fun bindImageRemoteDatasource(impl: ImageRemoteDatasourceImpl): ImageRemoteDatasource
}