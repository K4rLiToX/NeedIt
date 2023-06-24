package com.carlosdiestro.needit.features.friends

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val friendsRoute = "friends"

fun NavController.navigateToFriends() = navigate(friendsRoute)

fun NavGraphBuilder.friendsScreen(
    onFriendClick: (String) -> Unit
) {
    composable(route = friendsRoute) {
        FriendsRoute(
            onFriendClick = onFriendClick
        )
    }
}