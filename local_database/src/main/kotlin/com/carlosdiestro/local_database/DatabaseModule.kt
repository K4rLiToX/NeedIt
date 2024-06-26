package com.carlosdiestro.local_database

import android.content.Context
import androidx.room.Room
import com.carlosdiestro.local_database.friend_requests.FriendRequestDao
import com.carlosdiestro.local_database.friends.FriendDao
import com.carlosdiestro.local_database.wishes.WishDao
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

    @Singleton
    @Provides
    fun provideFriendDao(db: NeedItDatabase): FriendDao = db.friendDao()

    @Singleton
    @Provides
    fun provideFriendRequestDao(db: NeedItDatabase): FriendRequestDao = db.friendRequestDao()
}