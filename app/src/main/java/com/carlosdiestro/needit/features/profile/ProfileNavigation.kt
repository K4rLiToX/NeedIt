package com.carlosdiestro.needit.features.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val profileRoute = "profile"

fun NavController.navigateToProfile() = navigate(profileRoute)

fun NavGraphBuilder.profileScreen(
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit
) {
    composable(route = profileRoute) {
        ProfileRoute(
            onItemClick = onItemClick,
            onItemLongClick = onItemLongClick
        )
    }
}