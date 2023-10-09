package com.carlosdiestro.needit.features.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.carlosdiestro.needit.features.sign_in.signInRoute

const val homeRoute = "home"

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) = navigate(homeRoute, navOptions)

fun NavController.navigateToHomeCleaningBackStack() = navigate(homeRoute) {
    popUpTo(signInRoute) {
        inclusive = true
    }
}

fun NavGraphBuilder.homeScreen(
    onItemClick: (Long) -> Unit,
    onUpdateClick: (String, Int, Long) -> Unit
) {
    composable(route = homeRoute) {
        HomeRoute(
            onItemClick = onItemClick,
            onUpdateClick = onUpdateClick
        )
    }
}