package com.carlosdiestro.needit.core

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.carlosdiestro.needit.MainViewModel
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItFab
import com.carlosdiestro.needit.core.design_system.components.dialogs.CameraPermissionTextProvider
import com.carlosdiestro.needit.core.design_system.components.dialogs.PermissionDialog
import com.carlosdiestro.needit.core.design_system.components.navigation.NeedItBottomBar
import com.carlosdiestro.needit.core.design_system.components.navigation.TopLevelDestination
import com.carlosdiestro.needit.core.design_system.components.navigation.routes
import com.carlosdiestro.needit.core.design_system.theme.Icons
import com.carlosdiestro.needit.core.design_system.theme.icons
import com.carlosdiestro.needit.core.navigation.NeedItNavHost
import com.carlosdiestro.needit.features.home.navigateToHome
import com.carlosdiestro.needit.features.profile.navigateToProfile
import kotlinx.coroutines.CoroutineScope

@Composable
fun Main(
    appState: NeedItAppState,
    viewModel: MainViewModel = hiltViewModel(),
    launchCameraPermissionLauncher: () -> Unit,
    isCameraPermissionPermanentlyDeclined: Boolean,
    onGoToAppSettingsClick: () -> Unit,
    isSignedIn: Boolean
) {
    val currentDestinationRoute = appState.currentDestinationRoute
    val isUserGuest by viewModel.isUserGuest.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                NeedItBottomBar(
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
                        NeedItFab(
                            icon = painterResource(id = Icons.NeedIt),
                            onClick = {
                                if (currentDestinationRoute == TopLevelDestination.Home.route) {
                                    launchCameraPermissionLauncher()
                                }
                            }
                        )
                    }

                    TopLevelDestination.Friends.route -> {
                        NeedItFab(
                            icon = MaterialTheme.icons.AddFriend,
                            onClick = {
                                if (currentDestinationRoute == TopLevelDestination.Home.route) {
                                    launchCameraPermissionLauncher()
                                }
                            }
                        )
                    }

                    else -> Unit
                }
            }

        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        val window = (LocalView.current.context as Activity).window
        window.navigationBarColor = appState.navigationBarColor
        window.statusBarColor = appState.statusBarColor
        NeedItNavHost(
            appState = appState,
            isUserGuest = isUserGuest,
            isSignedIn = isSignedIn,
            modifier = Modifier.padding(it)
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
}

@Composable
fun rememberNeedItAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): NeedItAppState {
    return remember(
        navController,
        coroutineScope
    ) {
        NeedItAppState(
            navController = navController,
            coroutineScope = coroutineScope
        )
    }
}

@Stable
class NeedItAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentDestinationRoute: String
        @Composable get() = currentDestination?.route ?: ""

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()
    private val topLevelDestinationsWithFab: List<String> = topLevelDestinations
        .filter { it.hasFab }
        .routes()
    private val topLevelDestinationsRoutes: List<String> = topLevelDestinations.routes()

    val shouldShowFab: Boolean
        @Composable get() = currentDestinationRoute in topLevelDestinationsWithFab

    val shouldShowBottomBar: Boolean
        @Composable get() = isTopLevelDestination()

    var shouldShowCameraPermissionDialog by mutableStateOf(false)
        private set

    val navigationBarColor: Int
        @Composable get() =
            if (isTopLevelDestination()) {
                MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp).toArgb()
            } else {
                MaterialTheme.colorScheme.background.toArgb()
            }

    val statusBarColor: Int
        @Composable get() =
            when (currentDestinationRoute) {
                TopLevelDestination.Profile.route -> MaterialTheme.colorScheme
                    .surfaceColorAtElevation(3.dp).toArgb()

                else -> MaterialTheme.colorScheme.background.toArgb()
            }

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        when (topLevelDestination) {
            TopLevelDestination.Home -> navController.navigateToHome(
                topLevelNavOptions
            )

            TopLevelDestination.Gifts -> navController.navigate(
                topLevelDestination.route,
                topLevelNavOptions
            )

            TopLevelDestination.Friends -> navController.navigate(
                topLevelDestination.route,
                topLevelNavOptions
            )

            TopLevelDestination.Profile -> navController.navigateToProfile(
                topLevelNavOptions
            )
        }
    }

    fun setShowCameraPermissionDialog(shouldShow: Boolean) {
        shouldShowCameraPermissionDialog = shouldShow
    }

    @Composable
    private fun isTopLevelDestination(): Boolean =
        currentDestinationRoute in topLevelDestinationsRoutes
}