package com.carlosdiestro.needit.core

import android.content.Context
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.carlosdiestro.needit.core.design_system.components.navigation.navigation_bar.TopLevelDestination
import com.carlosdiestro.needit.core.design_system.components.navigation.navigation_bar.routes
import com.carlosdiestro.needit.features.camera.cameraRoute
import com.carlosdiestro.needit.features.home.navigateToHome
import com.carlosdiestro.needit.features.settings.settingsRoute
import com.carlosdiestro.needit.features.upsert_item.upsertRoute
import com.carlosdiestro.needit.features.wish_details.detailsRoute
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberNeedItAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    context: Context = LocalContext.current
): NeedItAppState {
    return remember(
        navController,
        coroutineScope,
        context
    ) {
        NeedItAppState(
            navController = navController,
            coroutineScope = coroutineScope,
            windowSizeClass = windowSizeClass,
            context = context
        )
    }
}

@Stable
class NeedItAppState constructor(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
    val context: Context
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    private val currentDestinationRoute: String
        @Composable get() = currentDestination?.route ?: ""

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    private val topLevelDestinationsRoutes: List<String> = topLevelDestinations.routes()

    val shouldShowBottomBar: Boolean
        @Composable get() = isTopLevelDestination()

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