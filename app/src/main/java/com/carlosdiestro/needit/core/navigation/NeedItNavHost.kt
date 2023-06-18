package com.carlosdiestro.needit.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import com.carlosdiestro.needit.core.NeedItAppState

@Composable
fun NeedItNavHost(
    appState: NeedItAppState,
    modifier: Modifier = Modifier,
    startDestination: String = ""
) {
    val navController = appState.navController
    val context = LocalContext.current

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

    }
}