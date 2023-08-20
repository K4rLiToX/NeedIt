package com.carlosdiestro.needit.data

import com.carlosdiestro.needit.domain.users.repository.UserRepository
import com.carlosdiestro.needit.data.users.repository.UserRepositoryImpl
import com.carlosdiestro.needit.domain.wishes.repository.WishRepository
import com.carlosdiestro.needit.data.wishes.repository.WishRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    fun bindWishRepository(impl: WishRepositoryImpl): WishRepository
}