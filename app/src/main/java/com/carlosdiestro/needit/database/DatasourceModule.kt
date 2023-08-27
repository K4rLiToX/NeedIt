package com.carlosdiestro.needit.database

import com.carlosdiestro.needit.data.users.datasources.UserLocalDatasource
import com.carlosdiestro.needit.data.wishes.datasources.WishLocalDatasource
import com.carlosdiestro.needit.data.wishes.datasources.WishLocalDatasourceImpl
import com.carlosdiestro.needit.database.datasources.UserLocalDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatasourceModule {

    @Singleton
    @Binds
    fun bindUserLocalDatasource(impl: UserLocalDatasourceImpl): UserLocalDatasource

    @Singleton
    @Binds
    fun bindWishLocalDatasource(impl: WishLocalDatasourceImpl): WishLocalDatasource
}