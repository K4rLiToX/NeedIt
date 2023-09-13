package com.carlosdiestro.needit.features.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val profileRoute = "profile"

fun NavController.navigateToProfile(
    navOptions: NavOptions? = null
) = navigate(profileRoute, navOptions)

fun NavGraphBuilder.profileScreen(
    isUserGuest: Boolean,
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit
) {
    composable(route = profileRoute) {
        ProfileRoute(
            isUserGuest = isUserGuest,
            onItemClick = onItemClick,
            onItemLongClick = onItemLongClick
        )
    }
}