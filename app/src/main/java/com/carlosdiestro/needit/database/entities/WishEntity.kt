package com.carlosdiestro.needit.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish_table")
class WishEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long?,
    @ColumnInfo(name = "cloud_id")
    val cloudId: String = "",
    @ColumnInfo(name = "user_id")
    val userId: String,
    @ColumnInfo(name = "image_local_path")
    val imageLocalPath: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String = "",
    @ColumnInfo(name = "price")
    val price: Double,
    @ColumnInfo(name = "description")
    val description: String = "",
    @ColumnInfo(name = "web_url")
    val webUrl: String = "",
    @ColumnInfo(name = "category")
    val category: Int,
    @ColumnInfo(name = "is_shared")
    val isShared: Boolean = false,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "subtitle")
    val subtitle: String,
    @ColumnInfo(name = "size")
    val size: String? = null,
    @ColumnInfo(name = "color")
    val color: String? = null,
    @ColumnInfo(name = "isbn")
    val isbn: String? = null
)