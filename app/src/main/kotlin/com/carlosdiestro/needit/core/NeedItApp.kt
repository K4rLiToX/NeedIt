package com.carlosdiestro.needit.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.carlosdiestro.design_system.animations.enter
import com.carlosdiestro.design_system.animations.exit
import com.carlosdiestro.design_system.animations.exitSlideDown
import com.carlosdiestro.design_system.animations.exitSlideUp
import com.carlosdiestro.design_system.animations.exitZSharedAxis
import com.carlosdiestro.design_system.animations.popEnterZSharedAxis
import com.carlosdiestro.design_system.navigation.top_app_bar.NiMainTopAppBar
import com.carlosdiestro.needit.core.navigation.NeedItNavHost
import com.carlosdiestro.needit.core.navigation.NiNavigationBar
import com.carlosdiestro.needit.features.account.AccountDialogRoute
import com.carlosdiestro.feature.home.HomeDestination
import com.carlosdiestro.needit.features.settings.navigateToSettings
import com.carlosdiestro.needit.features.sign_in.navigateToSignIn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeedItApp(
    windowSizeClass: WindowSizeClass,
    darkTheme: Boolean,
    appState: NeedItAppState = rememberNeedItAppState(
        windowSizeClass = windowSizeClass,
        darkTheme = darkTheme
    ),
    isSignedIn: Boolean,
    profilePictureUrl: String,
) {
    AnimatedVisibility(
        visible = appState.shouldShowAccountDialog,
        enter = enter,
        exit = exit
    ) {
        AccountDialogRoute(
            onDismiss = appState::closeAccountDialog,
            onSignOutClick = {
                appState.closeAccountDialog()
                appState.navController.navigateToSignIn(popUpTo = HomeDestination.route)
            },
            onSettingsClick = {
                appState.closeAccountDialog()
                appState.navController.navigateToSettings()
            }
        )
    }

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = appState.shouldShowTopBar,
                enter = popEnterZSharedAxis,
                exit = exitZSharedAxis + exitSlideUp,
            ) {
                NiMainTopAppBar(
                    accountImageUrl = profilePictureUrl,
                    onNotificationClick = {},
                    onAccountClick = { appState.openAccountDialog() }
                )
            }
        },
        bottomBar = {
            AnimatedVisibility(
                visible = appState.shouldShowBottomBar,
                enter = popEnterZSharedAxis,
                exit = exitZSharedAxis + exitSlideDown,
            ) {
                NiNavigationBar(
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination
                )
            }
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) {
        NeedItNavHost(
            appState = appState,
            isSignedIn = isSignedIn,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .consumeWindowInsets(it)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
                )
        )
    }
}