package com.carlosdiestro.local_database

import com.carlosdiestro.friend.data.FriendLocalDatasource
import com.carlosdiestro.friend.data.FriendRequestLocalDatasource
import com.carlosdiestro.local_database.friend_requests.FriendRequestLocalDatasourceImpl
import com.carlosdiestro.local_database.friends.FriendLocalDatasourceImpl
import com.carlosdiestro.local_database.wishes.WishLocalDatasourceImpl
import com.carlosdiestro.wish.data.datasource.WishLocalDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RoomDatasourceModule {

    @Singleton
    @Binds
    fun bindWishLocalDatasource(impl: WishLocalDatasourceImpl): WishLocalDatasource

    @Singleton
    @Binds
    fun bindFriendLocalDatasource(impl: FriendLocalDatasourceImpl): FriendLocalDatasource

    @Singleton
    @Binds
    fun bindFriendRequestLocalDatasource(impl: FriendRequestLocalDatasourceImpl): FriendRequestLocalDatasource
}