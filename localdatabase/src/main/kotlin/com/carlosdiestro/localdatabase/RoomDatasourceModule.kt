package com.carlosdiestro.localdatabase

import com.carlosdiestro.localdatabase.wishes.WishLocalDatasource
import com.carlosdiestro.localdatabase.wishes.WishLocalDatasourceImpl
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
}