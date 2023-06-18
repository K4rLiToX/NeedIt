package com.carlosdiestro.needit.core.design_system.components.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.carlosdiestro.needit.core.design_system.theme.icons

enum class TopLevelDestination(
    val icon: ImageVector,
    val hasFab: Boolean,
    val route: String
) {
    Home(
        icon = MaterialTheme.icons.Home,
        hasFab = true,
        route = ""
    ),
    Gifts(
        icon = MaterialTheme.icons.Gifts,
        hasFab = false,
        route = ""
    ),
    Friends(
        icon = MaterialTheme.icons.Friends,
        hasFab = true,
        route = ""
    ),
    Profile(
        icon = MaterialTheme.icons.Profile,
        hasFab = false,
        route = ""
    )
}

fun List<TopLevelDestination>.routes(): List<String> = this.map { it.route }

@Composable
fun NeedItBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
) {
    NavigationBar(
        tonalElevation = 0.dp
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            NeedItBottomBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = "Nav item icon"
                    )
                }
            )
        }
    }
}

@Composable
private fun RowScope.NeedItBottomBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = icon,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            indicatorColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination):
        Boolean =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false