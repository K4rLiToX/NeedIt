package com.carlosdiestro.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carlosdiestro.localdatabase.wishes.WishDao
import com.carlosdiestro.localdatabase.wishes.WishEntity

@Database(
    entities = [WishEntity::class],
    version = 1,
    exportSchema = false
)
internal abstract class NeedItDatabase : RoomDatabase() {
    abstract fun wishDao(): WishDao
}