package com.carlosdiestro.needit.core

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.carlosdiestro.needit.MainViewModel
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.buttons.NiDoubleButton
import com.carlosdiestro.needit.core.design_system.components.buttons.NiTextButton
import com.carlosdiestro.needit.core.design_system.components.extensions.conditional
import com.carlosdiestro.needit.core.design_system.components.fab.NiFab
import com.carlosdiestro.needit.core.design_system.components.menus.AppOption
import com.carlosdiestro.needit.core.design_system.components.menus.CameraPermissionTextProvider
import com.carlosdiestro.needit.core.design_system.components.menus.NiAccountDialog
import com.carlosdiestro.needit.core.design_system.components.menus.NiAccountDialogState
import com.carlosdiestro.needit.core.design_system.components.menus.PermissionDialog
import com.carlosdiestro.needit.core.design_system.components.menus.rememberNiAccountDialogState
import com.carlosdiestro.needit.core.design_system.components.navigation.navigation_bar.NiNavigationBar
import com.carlosdiestro.needit.core.design_system.components.navigation.navigation_bar.TopLevelDestination
import com.carlosdiestro.needit.core.design_system.components.navigation.navigation_bar.routes
import com.carlosdiestro.needit.core.design_system.components.navigation.top_app_bar.NiMainTopAppBar
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icons
import com.carlosdiestro.needit.core.navigation.NeedItNavHost
import com.carlosdiestro.needit.features.account.navigateToSettings
import com.carlosdiestro.needit.features.account.settingsRoute
import com.carlosdiestro.needit.features.camera.cameraRoute
import com.carlosdiestro.needit.features.home.navigateToHome
import com.carlosdiestro.needit.features.sign_in.navigateToSignIn
import com.carlosdiestro.needit.features.upsert_item.upsertRoute
import com.carlosdiestro.needit.features.wish_details.detailsRoute
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(
    appState: NeedItAppState,
    viewModel: MainViewModel = hiltViewModel(),
    launchCameraPermissionLauncher: () -> Unit,
    isCameraPermissionPermanentlyDeclined: Boolean,
    onGoToAppSettingsClick: () -> Unit
) {
    val currentDestinationRoute = appState.currentDestinationRoute
    val state by viewModel.state.collectAsStateWithLifecycle()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                viewModel.linkAccount(result.data ?: return@rememberLauncherForActivityResult)
            }
        }
    )

    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                appState.context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    LaunchedEffect(key1 = state.googleIntent) {
        if (state.googleIntent != null) {
            launcher.launch(
                IntentSenderRequest.Builder(
                    intentSender = state.googleIntent!!
                ).build()
            )
        }
    }

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
        NiAccountDialog(
            state = appState.accountDialogState,
            username = state.username,
            email = state.email,
            profilePictureUrl = state.profilePictureUrl,
            isUserAnonymous = state.isUserAnonymous,
            onDismiss = { appState.closeAccountDialog() },
            onAccountActionClick = {
                if (state.isUserAnonymous) {
                    viewModel.requestGoogleSignInIntent()
                } else {
                    viewModel.signOut()
                    appState.navController.navigateToSignIn()
                }
                appState.closeAccountDialog()
            },
            header = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            accountExtras = {
                AppOption(
                    icon = MaterialTheme.icons.Birthday,
                    labelId = R.string.profile_birthday,
                    value = "16/04",
                    onClick = {}
                )
                AppOption(
                    icon = MaterialTheme.icons.Currency,
                    labelId = R.string.profile_currency,
                    value = "EUR €",
                    onClick = {}
                )
            },
            appOptions = {
                AppOption(
                    icon = MaterialTheme.icons.Settings,
                    labelId = R.string.settings_title,
                    onClick = {
                        appState.navController.navigateToSettings()
                        appState.closeAccountDialog()
                    }
                )
                AppOption(
                    icon = MaterialTheme.icons.Feedback,
                    labelId = R.string.send_feedback_title,
                    onClick = {}
                )
                AppOption(
                    icon = MaterialTheme.icons.Bug,
                    labelId = R.string.report_bug_title,
                    onClick = {}
                )
            },
            footer = {
                NiDoubleButton(
                    leftButton = {
                        NiTextButton(
                            labelId = R.string.button_privacy_policy,
                            onClick = {}
                        )
                    },
                    rightButton = {
                        NiTextButton(
                            labelId = R.string.button_terms_of_service,
                            onClick = {}
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = MaterialTheme.dimensions.spacingL,
                            vertical = MaterialTheme.dimensions.spacingS
                        )
                )
            }
        )
    }
}

@Composable
fun rememberNeedItAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    context: Context = LocalContext.current,
    accountDialogState: NiAccountDialogState = rememberNiAccountDialogState()
): NeedItAppState {
    return remember(
        navController,
        coroutineScope,
        context,
        accountDialogState
    ) {
        NeedItAppState(
            navController = navController,
            coroutineScope = coroutineScope,
            context = context,
            accountDialogState = accountDialogState
        )
    }
}

@Stable
class NeedItAppState constructor(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val context: Context,
    val accountDialogState: NiAccountDialogState
) {
    private val routesWithoutStatusBarPadding: List<String> = listOf(
        detailsRoute,
        cameraRoute,
        upsertRoute,
        settingsRoute
    )

    private val routesWithoutNavigationBarPadding: List<String> = listOf(
        detailsRoute
    )

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentDestinationRoute: String
        @Composable get() = currentDestination?.route ?: ""

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()
    private val topLevelDestinationsWithFab: List<String> = listOf(
        TopLevelDestination.Home.route,
        TopLevelDestination.Friends.route
    )
    private val topLevelDestinationsRoutes: List<String> = topLevelDestinations.routes()

    val shouldShowFab: Boolean
        @Composable get() = currentDestinationRoute in topLevelDestinationsWithFab

    val shouldShowBottomBar: Boolean
        @Composable get() = isTopLevelDestination()

    var shouldShowCameraPermissionDialog by mutableStateOf(false)
        private set

    val shouldNotHaveStatusBarPadding: Boolean
        @Composable get() = currentDestinationRoute in routesWithoutStatusBarPadding

    val shouldNotHaveNavigationBarPadding: Boolean
        @Composable get() = currentDestinationRoute in routesWithoutNavigationBarPadding

    val shouldShowTopBar: Boolean
        @Composable get() = isTopLevelDestination()

    var shouldShowAccountDialog by mutableStateOf(false)
        private set

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
        }
    }

    fun setShowCameraPermissionDialog(shouldShow: Boolean) {
        shouldShowCameraPermissionDialog = shouldShow
    }

    @Composable
    private fun isTopLevelDestination(): Boolean =
        currentDestinationRoute in topLevelDestinationsRoutes

    fun closeAccountDialog() {
        shouldShowAccountDialog = false
    }

    fun openAccountDialog() {
        shouldShowAccountDialog = true
    }
}