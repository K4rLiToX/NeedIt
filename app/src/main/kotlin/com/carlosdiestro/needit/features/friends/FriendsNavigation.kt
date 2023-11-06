package com.carlosdiestro.needit.features.friends

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.carlosdiestro.needit.core.design_system.components.navigation.destinations.topLevelDestination

const val friendsRoute = "friends"

fun NavController.navigateToFriends() = navigate(friendsRoute)

fun NavGraphBuilder.friendsScreen(
    onFriendClick: (String) -> Unit
) {
    topLevelDestination(
        route = friendsRoute
    ) {
        FriendsRoute(
            onFriendClick = onFriendClick
        )
    }
}