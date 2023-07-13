package com.carlosdiestro.needit.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.carlosdiestro.needit.core.NeedItAppState
import com.carlosdiestro.needit.features.camera.cameraRoute
import com.carlosdiestro.needit.features.friends.friendsScreen
import com.carlosdiestro.needit.features.gifts.giftsScreen
import com.carlosdiestro.needit.features.home.homeRoute
import com.carlosdiestro.needit.features.home.homeScreen
import com.carlosdiestro.needit.features.home.navigateToHome
import com.carlosdiestro.needit.features.profile.profileScreen
import com.carlosdiestro.needit.features.upsert_item.navigateToUpsert
import com.carlosdiestro.needit.features.upsert_item.upsertRoute
import com.carlosdiestro.needit.features.wish_details.navigateToWishDetails
import com.carlosdiestro.needit.features.wish_details.wishDetailsRoute

@Composable
fun NeedItNavHost(
    appState: NeedItAppState,
    modifier: Modifier = Modifier,
    startDestination: String = homeRoute
) {
    val navController = appState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        homeScreen(
            coroutineScope = appState.coroutineScope,
            onItemClick = navController::navigateToWishDetails,
            onUpdateClick = navController::navigateToUpsert
        )
        giftsScreen(

        )
        friendsScreen(
            onFriendClick = {}
        )
        profileScreen(
            onItemClick = {},
            onItemLongClick = {}
        )

        cameraRoute(
            coroutineScope = appState.coroutineScope,
            onBackClick = navController::popBackStack,
            onContinueClick = navController::navigateToUpsert
        )

        upsertRoute(
            onBackClick = navController::popBackStack,
            navigateHome = navController::navigateToHome
        )

        wishDetailsRoute(
            onBackClick = navController::popBackStack
        )
    }
}