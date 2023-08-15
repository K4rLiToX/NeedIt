package com.carlosdiestro.needit.data.users

import com.carlosdiestro.needit.data.users.datasources.UserLocalDatasource
import com.carlosdiestro.needit.data.users.datasources.UserLocalDatasourceImpl
import com.carlosdiestro.needit.data.users.datasources.UserRemoteDatasource
import com.carlosdiestro.needit.data.users.datasources.UserRemoteDatasourceImpl
import com.carlosdiestro.needit.data.users.repository.UserRepository
import com.carlosdiestro.needit.data.users.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UserDataModule {
    @Singleton
    @Binds
    fun bindUserRemoteDatasource(impl: UserRemoteDatasourceImpl): UserRemoteDatasource

    @Singleton
    @Binds
    fun bindUserLocalDatasource(impl: UserLocalDatasourceImpl): UserLocalDatasource

    @Singleton
    @Binds
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}