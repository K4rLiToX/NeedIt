package com.carlosdiestro.needit

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.needit.core.Main
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.rememberNeedItAppState
import com.carlosdiestro.needit.features.camera.navigateToCamera
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        setContent {
            val state by viewModel.state.collectAsStateWithLifecycle()
            val darkTheme = shouldUseDarkTheme(state.useSystemScheme, state.isNightMode)
            NeedItTheme(
                isDarkTheme = darkTheme
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val appState = rememberNeedItAppState()
                    val permissions = arrayOf(
                        Manifest.permission.CAMERA,
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                            Manifest.permission.READ_MEDIA_IMAGES
                        else
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                    val multiplePermissionsResultLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.RequestMultiplePermissions(),
                        onResult = { permissions ->
                            val areGranted = permissions.values.reduce { acc, next -> acc && next }
                            if (areGranted) appState.navController.navigateToCamera()
                            else Unit
                        }
                    )
//                    val cameraPermissionResultLauncher = rememberLauncherForActivityResult(
//                        contract = ActivityResultContracts.RequestPermission(),
//                        onResult = { isGranted ->
//                            if (isGranted) appState.navController.navigateToCamera()
//                            else appState.setShowCameraPermissionDialog(true)
//                        }
//                    )
                    Main(
                        appState = appState,
                        viewModel = viewModel,
                        launchCameraPermissionLauncher = {
                            multiplePermissionsResultLauncher.launch(
                                permissions
                            )
                        },
                        isCameraPermissionPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                            Manifest.permission.CAMERA
                        ),
                        onGoToAppSettingsClick = ::openAppSettings
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

@Composable
private fun shouldUseDarkTheme(
    useSystemScheme: Boolean,
    isNightMode: Boolean
): Boolean {
    return if (useSystemScheme) isSystemInDarkTheme()
    else isNightMode
}