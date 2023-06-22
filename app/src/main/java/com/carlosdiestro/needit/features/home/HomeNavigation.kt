package com.carlosdiestro.needit.features.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val homeRoute = "home"

fun NavController.navigateToHome() = navigate(homeRoute)

fun NavGraphBuilder.homeScreen(
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit
) {
    composable(route = homeRoute) {
        HomeRoute(
            onItemClick = onItemClick,
            onItemLongClick = onItemLongClick
        )
    }
}