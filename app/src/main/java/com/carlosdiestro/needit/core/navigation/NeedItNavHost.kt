package com.carlosdiestro.needit.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.carlosdiestro.needit.core.NeedItAppState
import com.carlosdiestro.needit.features.friends.friendsScreen
import com.carlosdiestro.needit.features.gifts.giftsScreen
import com.carlosdiestro.needit.features.home.homeRoute
import com.carlosdiestro.needit.features.home.homeScreen
import com.carlosdiestro.needit.features.profile.profileScreen

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
            onItemClick = {},
            onItemLongClick = {}
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
    }
}