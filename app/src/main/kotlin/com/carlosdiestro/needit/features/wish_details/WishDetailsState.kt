package com.carlosdiestro.needit.features.wish_details

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.vector.ImageVector
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.theme.icons

internal data class WishDetailsDataState(
    val id: Long = -1,
    val imageUrl: String = "",
    val title: String = "",
    val subtitle: String = "",
    val price: String = "0.0",
    val description: String = "",
    val webUrl: String = "",
    val isShared: Boolean = false,
    val size: String? = null,
    val color: String? = null,
    val isbn: String? = null
) {
    val actionIcon: ImageVector
        get() {
            return if (isShared) MaterialTheme.icons.Lock
            else MaterialTheme.icons.Share
        }

    @get:StringRes
    val actionLabelId: Int
        get() {
            return if (isShared) R.string.button_keep_private
            else R.string.button_share
        }
}