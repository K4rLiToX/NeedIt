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
import androidx.navigation.navOptions
import com.carlosdiestro.needit.core.NeedItAppState
import com.carlosdiestro.needit.features.camera.CameraDestination
import com.carlosdiestro.needit.features.camera.cameraScreen
import com.carlosdiestro.needit.features.camera.navigateToCamera
import com.carlosdiestro.needit.features.friends.friendsScreen
import com.carlosdiestro.needit.features.gifts.giftsScreen
import com.carlosdiestro.needit.features.home.HomeDestination
import com.carlosdiestro.needit.features.home.homeScreen
import com.carlosdiestro.needit.features.home.navigateToHome
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
        enterTransition = { com.carlosdiestro.design_system.animations.enterNone },
        exitTransition = { com.carlosdiestro.design_system.animations.exitNone },
        popEnterTransition = { com.carlosdiestro.design_system.animations.enterNone },
        popExitTransition = { com.carlosdiestro.design_system.animations.exitNone }
    ) {
        signInScreen(
            onSignInSuccessful = {
                navController.navigateToHome(
                    navOptions = navOptions {
                        popUpTo(SignInDestination.route) { inclusive = true }
                    }
                )
            }
        )

        homeScreen(
            onItemClick = navController::navigateToWishDetails,
            onUpdateClick = navController::navigateToUpsert,
            onCreateClick = navController::navigateToCamera,
            enterTransition = { com.carlosdiestro.design_system.animations.enterFadeThrough },
            exitTransition = {
                when (targetState.destination.route) {
                    in appState.topLevelDestinationsRoutes -> com.carlosdiestro.design_system.animations.exitFadeThrough
                    else -> com.carlosdiestro.design_system.animations.exitZSharedAxis
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    in appState.topLevelDestinationsRoutes -> com.carlosdiestro.design_system.animations.enterFadeThrough
                    else -> com.carlosdiestro.design_system.animations.popEnterZSharedAxis
                }
            },
            popExitTransition = { com.carlosdiestro.design_system.animations.exitFadeThrough }
        )

        giftsScreen(
            enterTransition = { com.carlosdiestro.design_system.animations.enterNone },
            exitTransition = { com.carlosdiestro.design_system.animations.exitNone },
            popEnterTransition = { com.carlosdiestro.design_system.animations.enterNone },
            popExitTransition = { com.carlosdiestro.design_system.animations.exitNone }
        )

        friendsScreen(
            onFriendClick = {},
            enterTransition = { com.carlosdiestro.design_system.animations.enterNone },
            exitTransition = { com.carlosdiestro.design_system.animations.exitNone },
            popEnterTransition = { com.carlosdiestro.design_system.animations.enterNone },
            popExitTransition = { com.carlosdiestro.design_system.animations.exitNone }
        )

        cameraScreen(
            onBackClick = navController::popBackStack,
            onContinueClick = navController::navigateToUpsert,
            enterTransition = { com.carlosdiestro.design_system.animations.enterZSharedAxis },
            exitTransition = {
                if (targetState.destination.route == UpsertDestination.route) {
                    com.carlosdiestro.design_system.animations.exitXSharedAxis
                } else {
                    com.carlosdiestro.design_system.animations.exitNone
                }
            },
            popEnterTransition = {
                if (initialState.destination.route == UpsertDestination.route) {
                    com.carlosdiestro.design_system.animations.popEnterXSharedAxis
                } else {
                    com.carlosdiestro.design_system.animations.enterNone
                }
            },
            popExitTransition = { com.carlosdiestro.design_system.animations.popExitZSharedAxis }
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
                    com.carlosdiestro.design_system.animations.enterXSharedAxis
                } else {
                    com.carlosdiestro.design_system.animations.enterSlideUp
                }
            },
            exitTransition = { com.carlosdiestro.design_system.animations.exitNone },
            popEnterTransition = { com.carlosdiestro.design_system.animations.enterNone },
            popExitTransition = {
                if (targetState.destination.route == CameraDestination.route) {
                    com.carlosdiestro.design_system.animations.popExitXSharedAxis
                } else {
                    com.carlosdiestro.design_system.animations.slideDown
                }
            }
        )

        wishDetailsScreen(
            onBackClick = navController::popBackStack,
            onUpdateClick = navController::navigateToUpsert,
            enterTransition = { com.carlosdiestro.design_system.animations.enterZSharedAxis },
            exitTransition = { fadeOut(tween()) },
            popEnterTransition = { fadeIn(tween()) },
            popExitTransition = { com.carlosdiestro.design_system.animations.popExitZSharedAxis }
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