package com.carlosdiestro.needit.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.needit.MainViewModel
import com.carlosdiestro.needit.core.design_system.components.extensions.conditional
import com.carlosdiestro.needit.core.design_system.components.fab.NiFab
import com.carlosdiestro.needit.core.design_system.components.menus.CameraPermissionTextProvider
import com.carlosdiestro.needit.core.design_system.components.menus.PermissionDialog
import com.carlosdiestro.needit.core.design_system.components.navigation.navigation_bar.NiNavigationBar
import com.carlosdiestro.needit.core.design_system.components.navigation.navigation_bar.TopLevelDestination
import com.carlosdiestro.needit.core.design_system.components.navigation.top_app_bar.NiMainTopAppBar
import com.carlosdiestro.needit.core.design_system.theme.icons
import com.carlosdiestro.needit.core.navigation.NeedItNavHost
import com.carlosdiestro.needit.features.account.AccountDialogRoute
import com.carlosdiestro.needit.features.settings.navigateToSettings
import com.carlosdiestro.needit.features.sign_in.navigateToSignIn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeedItApp(
    appState: NeedItAppState,
    viewModel: MainViewModel,
    launchCameraPermissionLauncher: () -> Unit,
    isCameraPermissionPermanentlyDeclined: Boolean,
    onGoToAppSettingsClick: () -> Unit
) {
    val currentDestinationRoute = appState.currentDestinationRoute
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            if (appState.shouldShowTopBar) {
                NiMainTopAppBar(
                    accountImageUrl = state.profilePictureUrl,
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
        },
        floatingActionButton = {
            if (appState.shouldShowFab) {
                when (currentDestinationRoute) {
                    TopLevelDestination.Home.route -> {
                        NiFab(
                            icon = MaterialTheme.icons.Add,
                            onClick = {
                                if (currentDestinationRoute == TopLevelDestination.Home.route) {
                                    launchCameraPermissionLauncher()
                                }
                            }
                        )
                    }

                    TopLevelDestination.Friends.route -> {
                        NiFab(
                            icon = MaterialTheme.icons.AddFriend,
                            onClick = {}
                        )
                    }

                    else -> Unit
                }
            }

        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        NeedItNavHost(
            appState = appState,
            isUserAnonymous = state.isUserAnonymous,
            isSignedIn = state.isSignedIn,
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

    if (appState.shouldShowCameraPermissionDialog) {
        PermissionDialog(
            permissionTextProvider = CameraPermissionTextProvider(),
            isPermanentlyDeclined = isCameraPermissionPermanentlyDeclined,
            onDismiss = { appState.setShowCameraPermissionDialog(false) },
            onRequestClick = {
                launchCameraPermissionLauncher()
                appState.setShowCameraPermissionDialog(false)
            },
            onGoToAppSettingsClick = {
                onGoToAppSettingsClick()
                appState.setShowCameraPermissionDialog(false)
            },
            modifier = Modifier.fillMaxWidth()
        )
    }

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
}