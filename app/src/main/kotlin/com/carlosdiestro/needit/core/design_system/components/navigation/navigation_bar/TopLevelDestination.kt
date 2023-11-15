package com.carlosdiestro.needit.core.design_system.components.navigation.navigation_bar

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.vector.ImageVector
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.theme.icons
import com.carlosdiestro.needit.features.friends.FriendsDestination
import com.carlosdiestro.needit.features.gifts.GiftsDestination
import com.carlosdiestro.needit.features.home.HomeDestination

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String,
    @StringRes val labelId: Int
) {
    Home(
        selectedIcon = MaterialTheme.icons.HomeFilled,
        unselectedIcon = MaterialTheme.icons.HomeOutlined,
        route = HomeDestination.route,
        labelId = R.string.home_title
    ),
    Gifts(
        selectedIcon = MaterialTheme.icons.GiftsFilled,
        unselectedIcon = MaterialTheme.icons.GiftsOutlined,
        route = GiftsDestination.route,
        labelId = R.string.gifts_title
    ),
    Friends(
        selectedIcon = MaterialTheme.icons.FriendsFilled,
        unselectedIcon = MaterialTheme.icons.FriendsOutlined,
        route = FriendsDestination.route,
        labelId = R.string.friends_title
    )
}

fun List<TopLevelDestination>.routes(): List<String> = this.map { it.route }