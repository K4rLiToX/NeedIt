package com.carlosdiestro.needit.network.dtos

data class WishDto(
    val id: Long,
    val userId: String,
    val imageUrl: String,
    val price: Double,
    val description: String,
    val webUrl: String,
    @field: JvmField val isShared: Boolean,
    val category: Int,
    val title: String,
    val subtitle: String,
    val size: String?,
    val color: String?,
    val isbn: String?
)