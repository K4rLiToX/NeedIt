package com.carlosdiestro.remote_database.firestore.wishes

data class WishDto(
    val id: String,
    val userId: String,
    val imageUrl: String,
    val price: Double,
    val description: String,
    val webUrl: String,
    @field: JvmField val isShared: Boolean,
    val category: Int,
    val title: String,
    val subtitle: String,
    val size: String? = null,
    val color: String? = null,
    val isbn: String? = null
)