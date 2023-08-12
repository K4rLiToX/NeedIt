package com.carlosdiestro.needit.features.sign_in

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val signInRoute = "sign_in"

fun NavController.navigateToSignIn(

) = navigate(signInRoute)

fun NavGraphBuilder.signInScreen(

) {
    composable(route = signInRoute) {
        SignInRoute()
    }
}