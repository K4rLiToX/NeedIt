package com.carlosdiestro.needit.core.navigation

import android.app.Activity
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import com.carlosdiestro.needit.core.NeedItAppState
import com.carlosdiestro.needit.core.design_system.components.animations.enterFadeThrough
import com.carlosdiestro.needit.core.design_system.components.animations.enterNone
import com.carlosdiestro.needit.core.design_system.components.animations.enterSlideUp
import com.carlosdiestro.needit.core.design_system.components.animations.enterXSharedAxis
import com.carlosdiestro.needit.core.design_system.components.animations.enterZSharedAxis
import com.carlosdiestro.needit.core.design_system.components.animations.exitFadeThrough
import com.carlosdiestro.needit.core.design_system.components.animations.exitNone
import com.carlosdiestro.needit.core.design_system.components.animations.exitXSharedAxis
import com.carlosdiestro.needit.core.design_system.components.animations.exitZSharedAxis
import com.carlosdiestro.needit.core.design_system.components.animations.popEnterXSharedAxis
import com.carlosdiestro.needit.core.design_system.components.animations.popEnterZSharedAxis
import com.carlosdiestro.needit.core.design_system.components.animations.popExitXSharedAxis
import com.carlosdiestro.needit.core.design_system.components.animations.popExitZSharedAxis
import com.carlosdiestro.needit.core.design_system.components.animations.slideDown
import com.carlosdiestro.needit.features.camera.CameraDestination
import com.carlosdiestro.needit.features.camera.cameraScreen
import com.carlosdiestro.needit.features.camera.navigateToCamera
import com.carlosdiestro.needit.features.friends.friendsScreen
import com.carlosdiestro.needit.features.gifts.giftsScreen
import com.carlosdiestro.needit.features.home.HomeDestination
import com.carlosdiestro.needit.features.home.homeScreen
import com.carlosdiestro.needit.features.home.navigateToHomeCleaningBackStack
import com.carlosdiestro.needit.features.settings.settingsScreen
import com.carlosdiestro.needit.features.sign_in.SignInDestination
import com.carlosdiestro.needit.features.sign_in.signInScreen
import com.carlosdiestro.needit.features.upsert_item.UpsertDestination
import com.carlosdiestro.needit.features.upsert_item.navigateToUpsert
import com.carlosdiestro.needit.features.upsert_item.upsertScreen
import com.carlosdiestro.needit.features.wish_details.WishDetailsDestination
import com.carlosdiestro.needit.features.wish_details.navigateToWishDetails
import com.carlosdiestro.needit.features.wish_details.wishDetailsScreen

@Composable
fun NeedItNavHost(
    appState: NeedItAppState,
    isSignedIn: Boolean,
    modifier: Modifier = Modifier
) {
    val navController = appState.navController
    val startDestination = if (isSignedIn) HomeDestination.route else SignInDestination.route

    UpdateStatusBarContentColor(
        currentRoute = appState.currentDestinationRoute,
        darkTheme = appState.darkTheme
    )

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
            onSignInSuccessful = {
                navController.navigateToHomeCleaningBackStack(popUpTo = SignInDestination.route)
            }
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
            onContinueClick = navController::navigateToUpsert,
            enterTransition = { enterZSharedAxis },
            exitTransition = {
                if (targetState.destination.route == UpsertDestination.route) {
                    exitXSharedAxis
                } else {
                    exitNone
                }
            },
            popEnterTransition = {
                if (initialState.destination.route == UpsertDestination.route) {
                    popEnterXSharedAxis
                } else {
                    enterNone
                }
            },
            popExitTransition = { popExitZSharedAxis }
        )

        upsertScreen(
            onBackClick = navController::popBackStack,
            onFinish = {
                val previousScreenRoute = navController.previousBackStackEntry?.destination?.route
                if (previousScreenRoute == CameraDestination.route) navController.popBackStack(
                    HomeDestination.route,
                    false
                )
                else navController.popBackStack()
            },
            enterTransition = {
                if (initialState.destination.route == CameraDestination.route) {
                    enterXSharedAxis
                } else {
                    enterSlideUp
                }
            },
            exitTransition = { exitNone },
            popEnterTransition = { enterNone },
            popExitTransition = {
                if (targetState.destination.route == CameraDestination.route) {
                    popExitXSharedAxis
                } else {
                    slideDown
                }
            }
        )

        wishDetailsScreen(
            onBackClick = navController::popBackStack,
            onUpdateClick = navController::navigateToUpsert,
            enterTransition = { enterZSharedAxis },
            exitTransition = { fadeOut(tween()) },
            popEnterTransition = { fadeIn(tween()) },
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

@Composable
private fun UpdateStatusBarContentColor(
    currentRoute: String,
    darkTheme: Boolean
) {
    val view = LocalView.current
    val window = (view.context as Activity).window
    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
        when (currentRoute) {
            CameraDestination.route, WishDetailsDestination.route -> false
            else -> !darkTheme
        }
}