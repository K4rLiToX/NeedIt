package com.carlosdiestro.needit.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.carlosdiestro.needit.core.design_system.components.extensions.conditional
import com.carlosdiestro.needit.core.design_system.components.navigation.navigation_bar.NiNavigationBar
import com.carlosdiestro.needit.core.design_system.components.navigation.top_app_bar.NiMainTopAppBar
import com.carlosdiestro.needit.core.navigation.NeedItNavHost
import com.carlosdiestro.needit.features.account.AccountDialogRoute
import com.carlosdiestro.needit.features.settings.navigateToSettings
import com.carlosdiestro.needit.features.sign_in.navigateToSignIn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeedItApp(
    windowSizeClass: WindowSizeClass,
    appState: NeedItAppState = rememberNeedItAppState(windowSizeClass = windowSizeClass),
    isSignedIn: Boolean,
    profilePictureUrl: String,
) {
    if (appState.shouldShowAccountDialog) {
        AccountDialogRoute(
            onDismiss = appState::closeAccountDialog,
            onSignOutClick = {
                appState.closeAccountDialog()
                appState.navController.navigateToSignIn()
            },
            onSettingsClick = {
                appState.closeAccountDialog()
                appState.navController.navigateToSettings()
            }
        )
    }

    Scaffold(
        topBar = {
            if (appState.shouldShowTopBar) {
                NiMainTopAppBar(
                    accountImageUrl = profilePictureUrl,
                    onNotificationClick = {},
                    onAccountClick = { appState.openAccountDialog() }
                )
            }
        },
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                NiNavigationBar(
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination
                )
            }
        }
    ) {
        NeedItNavHost(
            appState = appState,
            isSignedIn = isSignedIn,
            modifier = Modifier
                .conditional(
                    condition = appState.shouldNotHaveStatusBarPadding,
                    ifTrue = {
                        this
                    },
                    ifFalse = {
                        padding(top = it.calculateTopPadding())
                    }
                )
                .conditional(
                    condition = appState.shouldNotHaveNavigationBarPadding,
                    ifTrue = {
                        this
                    },
                    ifFalse = {
                        padding(bottom = it.calculateBottomPadding())
                    }
                )
        )
    }
}