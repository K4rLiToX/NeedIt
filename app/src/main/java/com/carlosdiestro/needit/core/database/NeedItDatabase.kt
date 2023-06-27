package com.carlosdiestro.needit.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carlosdiestro.needit.core.database.dao.WishDao
import com.carlosdiestro.needit.core.database.entities.WishEntity

@Database(
    entities = [WishEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NeedItDatabase: RoomDatabase() {
    abstract fun wishDao(): WishDao
}