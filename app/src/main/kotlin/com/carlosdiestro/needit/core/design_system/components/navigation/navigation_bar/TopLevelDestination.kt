package com.carlosdiestro.needit.core.design_system.components.navigation.navigation_bar

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.vector.ImageVector
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.theme.icons
import com.carlosdiestro.needit.features.friends.friendsRoute
import com.carlosdiestro.needit.features.gifts.giftsRoute
import com.carlosdiestro.needit.features.home.homeRoute

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String,
    @StringRes val labelId: Int
) {
    Home(
        selectedIcon = MaterialTheme.icons.Home,
        unselectedIcon = MaterialTheme.icons.Home,
        route = homeRoute,
        labelId = R.string.home_title
    ),
    Gifts(
        selectedIcon = MaterialTheme.icons.Gifts,
        unselectedIcon = MaterialTheme.icons.Gifts,
        route = giftsRoute,
        labelId = R.string.gifts_title
    ),
    Friends(
        selectedIcon = MaterialTheme.icons.Friends,
        unselectedIcon = MaterialTheme.icons.Friends,
        route = friendsRoute,
        labelId = R.string.friends_title
    )
}

fun List<TopLevelDestination>.routes(): List<String> = this.map { it.route }