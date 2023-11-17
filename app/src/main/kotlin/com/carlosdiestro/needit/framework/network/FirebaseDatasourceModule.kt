package com.carlosdiestro.needit.framework.network

import com.carlosdiestro.needit.data.users.UserRemoteDatasource
import com.carlosdiestro.needit.data.wishes.datasources.ImageRemoteDatasource
import com.carlosdiestro.needit.data.wishes.datasources.WishRemoteDatasource
import com.carlosdiestro.needit.framework.network.images.ImageRemoteDatasourceImpl
import com.carlosdiestro.needit.framework.network.users.UserRemoteDatasourceImpl
import com.carlosdiestro.needit.framework.network.wishes.WishRemoteDatasourceImpl
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
    fun bindImageRemoteDatasource(impl: ImageRemoteDatasourceImpl): ImageRemoteDatasource
}