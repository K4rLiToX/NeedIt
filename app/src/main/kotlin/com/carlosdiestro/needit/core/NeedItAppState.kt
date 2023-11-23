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
import com.carlosdiestro.feature.friends.navigateToFriends
import com.carlosdiestro.feature.gifts.navigateToGifts
import com.carlosdiestro.feature.home.navigateToHome
import com.carlosdiestro.needit.core.navigation.TopLevelDestination
import com.carlosdiestro.needit.core.navigation.routes
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberNeedItAppState(
    windowSizeClass: WindowSizeClass,
    darkTheme: Boolean,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    context: Context = LocalContext.current
): NeedItAppState {
    return remember(
        navController,
        coroutineScope
    ) {
        NeedItAppState(
            navController = navController,
            coroutineScope = coroutineScope,
            windowSizeClass = windowSizeClass,
            darkTheme = darkTheme,
            context = context
        )
    }
}

@Stable
class NeedItAppState constructor(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
    val darkTheme: Boolean,
    val context: Context
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentDestinationRoute: String
        @Composable get() = currentDestination?.route ?: ""

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    val topLevelDestinationsRoutes: List<String> = topLevelDestinations.routes()

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

            TopLevelDestination.Gifts -> navController.navigateToGifts(
                topLevelNavOptions
            )

            TopLevelDestination.Friends -> navController.navigateToFriends(
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