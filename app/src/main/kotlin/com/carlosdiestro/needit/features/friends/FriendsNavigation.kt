package com.carlosdiestro.needit.features.friends

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.carlosdiestro.needit.core.design_system.components.navigation.destinations.topLevelDestination

const val friendsRoute = "friends"

fun NavController.navigateToFriends() = navigate(friendsRoute)

fun NavGraphBuilder.friendsScreen(
    onFriendClick: (String) -> Unit,
    enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition,
    popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition
) {
    topLevelDestination(
        route = friendsRoute,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    ) {
        FriendsRoute(
            onFriendClick = onFriendClick
        )
    }
}