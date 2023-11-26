package com.carlosdiestro.needit.di

import com.carlosdiestro.friend.data.FriendRepositoryImpl
import com.carlosdiestro.friend.domain.FriendRepository
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
}