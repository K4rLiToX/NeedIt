package com.carlosdiestro.needit

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.carlosdiestro.needit.auth.GoogleAuthUiClient
import com.carlosdiestro.needit.core.Main
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.rememberNeedItAppState
import com.carlosdiestro.needit.features.camera.navigateToCamera
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var googleAuthUiClient: GoogleAuthUiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            NeedItTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val appState = rememberNeedItAppState()
                    val cameraPermissionResultLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.RequestPermission(),
                        onResult = { isGranted ->
                            if (isGranted) appState.navController.navigateToCamera()
                            else appState.setShowCameraPermissionDialog(true)
                        }
                    )
                    val isSignedIn = googleAuthUiClient.getSignedInUser() != null
                    Main(
                        appState = appState,
                        launchCameraPermissionLauncher = {
                            cameraPermissionResultLauncher.launch(
                                Manifest.permission.CAMERA
                            )
                        },
                        isCameraPermissionPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                            Manifest.permission.CAMERA
                        ),
                        onGoToAppSettingsClick = ::openAppSettings,
                        isSignedIn = isSignedIn
                    )
                }
            }
        }
    }
}

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}