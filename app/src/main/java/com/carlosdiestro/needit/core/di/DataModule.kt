package com.carlosdiestro.needit.core.di

import com.carlosdiestro.needit.data.users.UserRemoteDatasource
import com.carlosdiestro.needit.data.users.UserRemoteDatasourceImpl
import com.carlosdiestro.needit.data.users.UserRepository
import com.carlosdiestro.needit.data.users.UserRepositoryImpl
import com.carlosdiestro.needit.database.datasources.WishLocalDatasourceImpl
import com.carlosdiestro.needit.data.wishes.WishLocalDatasource
import com.carlosdiestro.needit.data.wishes.WishRepositoryImpl
import com.carlosdiestro.needit.domain.wishes.WishRepository
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
    fun bindWishLocalDataSource(impl: WishLocalDatasourceImpl): WishLocalDatasource

    @Singleton
    @Binds
    fun bindWishRepository(impl: WishRepositoryImpl): WishRepository

    @Singleton
    @Binds
    fun bindUserRemoteDatasource(impl: UserRemoteDatasourceImpl): UserRemoteDatasource

    @Singleton
    @Binds
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

}