package com.carlosdiestro.needit.core.di

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
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindWishLocalDataSource(impl: WishLocalDatasourceImpl): WishLocalDatasource

    @Singleton
    @Binds
    abstract fun bindWishRepository(impl: WishRepositoryImpl): WishRepository
}