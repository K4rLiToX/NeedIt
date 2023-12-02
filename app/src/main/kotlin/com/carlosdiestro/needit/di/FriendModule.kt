package com.carlosdiestro.needit.di

import com.carlosdiestro.friend.data.FriendRepositoryImpl
import com.carlosdiestro.friend.data.FriendRequestRepositoryImpl
import com.carlosdiestro.friend.data.FriendshipRepositoryImpl
import com.carlosdiestro.friend.domain.FriendRepository
import com.carlosdiestro.friend.domain.FriendRequestRepository
import com.carlosdiestro.friend.domain.FriendshipRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface FriendModule {

    @Binds
    @Singleton
    fun bindFriendRepository(impl: FriendRepositoryImpl): FriendRepository

    @Binds
    @Singleton
    fun bindFriendRequestRepository(impl: FriendRequestRepositoryImpl): FriendRequestRepository

    @Binds
    @Singleton
    fun bindFriendshipRepository(impl: FriendshipRepositoryImpl): FriendshipRepository
}