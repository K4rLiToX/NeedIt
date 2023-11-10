package com.carlosdiestro.needit.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.carlosdiestro.needit.core.NeedItAppState
import com.carlosdiestro.needit.core.design_system.components.animations.enterFadeThrough
import com.carlosdiestro.needit.core.design_system.components.animations.enterNone
import com.carlosdiestro.needit.core.design_system.components.animations.enterZSharedAxis
import com.carlosdiestro.needit.core.design_system.components.animations.exitFadeThrough
import com.carlosdiestro.needit.core.design_system.components.animations.exitNone
import com.carlosdiestro.needit.core.design_system.components.animations.exitZSharedAxis
import com.carlosdiestro.needit.core.design_system.components.animations.popEnterZSharedAxis
import com.carlosdiestro.needit.core.design_system.components.animations.popExitZSharedAxis
import com.carlosdiestro.needit.features.camera.cameraRoute
import com.carlosdiestro.needit.features.camera.cameraScreen
import com.carlosdiestro.needit.features.camera.navigateToCamera
import com.carlosdiestro.needit.features.friends.friendsScreen
import com.carlosdiestro.needit.features.gifts.giftsScreen
import com.carlosdiestro.needit.features.home.homeRoute
import com.carlosdiestro.needit.features.home.homeScreen
import com.carlosdiestro.needit.features.home.navigateToHomeCleaningBackStack
import com.carlosdiestro.needit.features.settings.settingsScreen
import com.carlosdiestro.needit.features.sign_in.signInRoute
import com.carlosdiestro.needit.features.sign_in.signInScreen
import com.carlosdiestro.needit.features.upsert_item.navigateToUpsert
import com.carlosdiestro.needit.features.upsert_item.upsertScreen
import com.carlosdiestro.needit.features.wish_details.navigateToWishDetails
import com.carlosdiestro.needit.features.wish_details.wishDetailsScreen

@Composable
fun NeedItNavHost(
    appState: NeedItAppState,
    isSignedIn: Boolean,
    modifier: Modifier = Modifier
) {
    val navController = appState.navController
    val startDestination = if (isSignedIn) homeRoute else signInRoute

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        enterTransition = { enterNone },
        exitTransition = { exitNone },
        popEnterTransition = { enterNone },
        popExitTransition = { exitNone }
    ) {
        signInScreen(
            onSignInSuccessful = navController::navigateToHomeCleaningBackStack
        )

        homeScreen(
            onItemClick = navController::navigateToWishDetails,
            onUpdateClick = navController::navigateToUpsert,
            onCreateClick = navController::navigateToCamera,
            enterTransition = { enterFadeThrough },
            exitTransition = {
                when (targetState.destination.route) {
                    in appState.topLevelDestinationsRoutes -> exitFadeThrough
                    else -> exitZSharedAxis
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    in appState.topLevelDestinationsRoutes -> enterFadeThrough
                    else -> popEnterZSharedAxis
                }
            },
            popExitTransition = { exitFadeThrough }
        )

        giftsScreen(
            enterTransition = { enterNone },
            exitTransition = { exitNone },
            popEnterTransition = { enterNone },
            popExitTransition = { exitNone }
        )

        friendsScreen(
            onFriendClick = {},
            enterTransition = { enterNone },
            exitTransition = { exitNone },
            popEnterTransition = { enterNone },
            popExitTransition = { exitNone }
        )

        cameraScreen(
            onBackClick = navController::popBackStack,
            onContinueClick = navController::navigateToUpsert
        )

        upsertScreen(
            onBackClick = navController::popBackStack,
            onFinish = {
                val previousScreenRoute = navController.previousBackStackEntry?.destination?.route
                if (previousScreenRoute == cameraRoute) navController.popBackStack(homeRoute, false)
                else navController.popBackStack()
            }
        )

        wishDetailsScreen(
            onBackClick = navController::popBackStack,
            onUpdateClick = navController::navigateToUpsert,
            enterTransition = { enterZSharedAxis },
            exitTransition = { exitNone },
            popEnterTransition = { enterNone },
            popExitTransition = { popExitZSharedAxis }
        )

        settingsScreen(
            onBackClick = navController::popBackStack,
            onVersionClick = {},
            onPrivacyPolicyClick = {},
            onTermsOfUseClick = {}
        )
    }
}