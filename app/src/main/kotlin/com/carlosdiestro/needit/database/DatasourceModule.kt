package com.carlosdiestro.needit.database

import com.carlosdiestro.needit.data.wishes.datasources.WishLocalDatasource
import com.carlosdiestro.needit.database.wishes.WishLocalDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DatasourceModule {

    @Singleton
    @Binds
    fun bindWishLocalDatasource(impl: WishLocalDatasourceImpl): WishLocalDatasource
}