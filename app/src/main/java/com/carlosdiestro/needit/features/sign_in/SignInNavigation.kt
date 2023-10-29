package com.carlosdiestro.needit.features.sign_in

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.carlosdiestro.needit.features.home.homeRoute

const val signInRoute = "sign_in"

fun NavController.navigateToSignIn() = navigate(signInRoute) {
    popUpTo(homeRoute) {
        inclusive = true
    }
}

fun NavGraphBuilder.signInScreen(
    onSignInSuccessful: () -> Unit
) {
    composable(route = signInRoute) {
        SignInRoute(
            onSignInSuccessful = onSignInSuccessful
        )
    }
}