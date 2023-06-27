package com.carlosdiestro.needit.features.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.coroutines.CoroutineScope

const val homeRoute = "home"

fun NavController.navigateToHome(
    navOptions: NavOptions?
) = navigate(homeRoute, navOptions)

fun NavGraphBuilder.homeScreen(
    coroutineScope: CoroutineScope,
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit
) {
    composable(route = homeRoute) {
        HomeRoute(
            coroutineScope = coroutineScope,
            onItemClick = onItemClick,
            onItemLongClick = onItemLongClick
        )
    }
}