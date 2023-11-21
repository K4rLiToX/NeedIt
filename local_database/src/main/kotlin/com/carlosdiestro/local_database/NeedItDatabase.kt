package com.carlosdiestro.local_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carlosdiestro.local_database.wishes.WishDao
import com.carlosdiestro.local_database.wishes.WishEntity

@Database(
    entities = [WishEntity::class],
    version = 1,
    exportSchema = false
)
internal abstract class NeedItDatabase : RoomDatabase() {
    abstract fun wishDao(): WishDao
}