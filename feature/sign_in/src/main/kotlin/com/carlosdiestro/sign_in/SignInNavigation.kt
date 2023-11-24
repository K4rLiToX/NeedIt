package com.carlosdiestro.sign_in

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val SIGN_IN_BASE_ROUTE = "sign_in"

object SignInDestination {

    const val route = SIGN_IN_BASE_ROUTE

    fun getDestination(): String {
        return SIGN_IN_BASE_ROUTE
    }
}

fun NavController.navigateToSignIn(popUpTo: String) = navigate(SignInDestination.getDestination()) {
    popUpTo(popUpTo) {
        inclusive = true
    }
}

fun NavGraphBuilder.signInScreen(
    onSignInSuccessful: () -> Unit
) {
    composable(route = SignInDestination.route) {
        SignInRoute(
            onSignInSuccessful = onSignInSuccessful
        )
    }
}