package com.carlosdiestro.localdatabase

import android.content.Context
import androidx.room.Room
import com.carlosdiestro.localdatabase.wishes.WishDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Singleton
    @Provides
    fun provideNeedItDatabase(
        @ApplicationContext context: Context
    ): NeedItDatabase = Room.databaseBuilder(
        context = context,
        klass = NeedItDatabase::class.java,
        name = "needit_db"
    ).build()

    @Singleton
    @Provides
    fun provideWishDao(db: NeedItDatabase): WishDao = db.wishDao()
}