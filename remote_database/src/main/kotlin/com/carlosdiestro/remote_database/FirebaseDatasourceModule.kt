package com.carlosdiestro.remote_database

import com.carlosdiestro.remote_database.firestore.users.UserRemoteDatasourceImpl
import com.carlosdiestro.remote_database.firestore.wishes.WishRemoteDatasourceImpl
import com.carlosdiestro.remote_database.storage.ImageRemoteDatasourceImpl
import com.carlosdiestro.user.data.UserRemoteDatasource
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
    fun bindUserRemoteDatasource(impl: UserRemoteDatasourceImpl): com.carlosdiestro.user.data.UserRemoteDatasource

    @Singleton
    @Binds
    fun bindWishRemoteDatasource(impl: WishRemoteDatasourceImpl): WishRemoteDatasource

    @Singleton
    @Binds
    fun bindImageRemoteDatasource(impl: ImageRemoteDatasourceImpl): ImageRemoteDatasource
}