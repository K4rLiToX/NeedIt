package com.carlosdiestro.needit.features.camera

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.coroutines.CoroutineScope

const val cameraRoute = "camera"

fun NavController.navigateToCamera() = navigate(cameraRoute)

fun NavGraphBuilder.cameraRoute(
    onBackClick: () -> Unit,
    onContinueClick: (String, Int, Long) -> Unit
) {
    composable(
        route = cameraRoute
    ) {
        CameraRoute(
            onBackClick = onBackClick,
            onContinueClick = onContinueClick
        )
    }
}