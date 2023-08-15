package com.carlosdiestro.needit.data.wishes

import com.carlosdiestro.needit.data.wishes.datasources.WishLocalDatasource
import com.carlosdiestro.needit.data.wishes.datasources.WishLocalDatasourceImpl
import com.carlosdiestro.needit.data.wishes.repository.WishRepository
import com.carlosdiestro.needit.data.wishes.repository.WishRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface WishDataModule {

    @Singleton
    @Binds
    fun bindWishLocalDatasource(impl: WishLocalDatasourceImpl): WishLocalDatasource

    @Singleton
    @Binds
    fun bindWishRepository(impl: WishRepositoryImpl): WishRepository

}