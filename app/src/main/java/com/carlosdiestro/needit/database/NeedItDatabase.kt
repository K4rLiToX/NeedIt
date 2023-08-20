package com.carlosdiestro.needit.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carlosdiestro.needit.database.dao.WishDao
import com.carlosdiestro.needit.database.entities.WishEntity

@Database(
    entities = [WishEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NeedItDatabase : RoomDatabase() {
    abstract val wishDao: WishDao
}