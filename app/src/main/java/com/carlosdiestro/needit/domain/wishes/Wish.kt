package com.carlosdiestro.needit.domain.wishes

import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory

data class Wish(
    val id: Long,
    val cloudId: String,
    val userId: String,
    val imageLocalPath: String,
    val imageUrl: String,
    val price: Double,
    val description: String,
    val webUrl: String,
    val isShared: Boolean,
    val category: WishCategory,
    val title: String,
    val subtitle: String,
    val size: String?,
    val color: String?,
    val isbn: String?
)