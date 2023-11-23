package com.carlosdiestro.needit.core.navigation

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.vector.ImageVector
import com.carlosdiestro.design_system.R
import com.carlosdiestro.design_system.theme.icons
import com.carlosdiestro.feature.friends.FriendsDestination
import com.carlosdiestro.feature.gifts.GiftsDestination
import com.carlosdiestro.feature.home.HomeDestination

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