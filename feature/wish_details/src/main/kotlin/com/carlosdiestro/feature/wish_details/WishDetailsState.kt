package com.carlosdiestro.feature.wish_details

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.carlosdiestro.design_system.i18n.Localization
import com.carlosdiestro.design_system.theme.icons

internal data class WishDetailsDataState(
    val id: String = "",
    val imageUrl: String = "",
    val title: String = "",
    val subtitle: String = "",
    val price: String = "0.0",
    val description: String = "",
    val webUrl: String = "",
    val isShared: Boolean = false,
    val size: String? = null,
    val color: String? = null,
    val isbn: String? = null,
    val isAnonymous: Boolean = true
) {
    val actionIcon: ImageVector
        get() {
            return if (isShared) MaterialTheme.icons.Lock
            else MaterialTheme.icons.Share
        }

    val actionLabel: String
        @Composable
        get() {
            return if (isShared) Localization.Button.KeepPrivate
            else Localization.Button.Share
        }
}