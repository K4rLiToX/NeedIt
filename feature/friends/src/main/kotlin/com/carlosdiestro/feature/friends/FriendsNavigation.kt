package com.carlosdiestro.feature.friends

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.carlosdiestro.design_system.navigation.destinations.topLevelDestination

private const val FRIENDS_BASE_ROUTE = "friends"

object FriendsDestination {

    const val route = FRIENDS_BASE_ROUTE

    fun getDestination(): String {
        return FRIENDS_BASE_ROUTE
    }
}

fun NavController.navigateToFriends(
    navOptions: NavOptions? = null
) = navigate(FriendsDestination.getDestination(), navOptions)

fun NavGraphBuilder.friendsScreen(
    onFriendClick: (String) -> Unit,
    enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition,
    popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition
) {
    topLevelDestination(
        route = FriendsDestination.route,
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