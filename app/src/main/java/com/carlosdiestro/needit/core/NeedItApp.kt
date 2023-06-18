package com.carlosdiestro.needit.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItFab
import com.carlosdiestro.needit.core.design_system.components.navigation.NeedItBottomBar
import com.carlosdiestro.needit.core.design_system.components.navigation.TopLevelDestination
import com.carlosdiestro.needit.core.design_system.components.navigation.routes
import com.carlosdiestro.needit.core.design_system.theme.icons
import com.carlosdiestro.needit.core.navigation.NeedItNavHost

@Composable
fun Main(
    appState: NeedItAppState
) {
    val currentDestinationRoute = appState.currentDestinationRoute

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
            val icon = when (currentDestinationRoute) {
                TopLevelDestination.Home.route -> MaterialTheme.icons.Warning
                TopLevelDestination.Friends.route -> MaterialTheme.icons.AddFriend
                else -> null
            }
            NeedItFab(
                icon = icon,
                onClick = {}
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        NeedItNavHost(
            appState = appState,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
fun rememberNeedItAppState(
    navController: NavHostController = rememberNavController()
): NeedItAppState {
    return remember(
        navController
    ) {
        NeedItAppState(
            navController = navController
        )
    }
}

@Stable
class NeedItAppState(
    val navController: NavHostController
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

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        when (topLevelDestination) {
            TopLevelDestination.Home -> navController.navigate(
                topLevelDestination.route,
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

            TopLevelDestination.Profile -> navController.navigate(
                topLevelDestination.route,
                topLevelNavOptions
            )
        }
    }

    @Composable
    private fun isTopLevelDestination(): Boolean =
        currentDestinationRoute in topLevelDestinationsRoutes
}