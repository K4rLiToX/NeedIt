package com.carlosdiestro.remotedatabase

import com.carlosdiestro.remotedatabase.firestore.users.UserRemoteDatasource
import com.carlosdiestro.remotedatabase.firestore.users.UserRemoteDatasourceImpl
import com.carlosdiestro.remotedatabase.firestore.wishes.WishRemoteDatasource
import com.carlosdiestro.remotedatabase.firestore.wishes.WishRemoteDatasourceImpl
import com.carlosdiestro.remotedatabase.storage.ImageRemoteDatasource
import com.carlosdiestro.remotedatabase.storage.ImageRemoteDatasourceImpl
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