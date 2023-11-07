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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.carlosdiestro.needit.core.NeedItApp
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.rememberNeedItAppState
import com.carlosdiestro.needit.features.camera.navigateToCamera
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var uiState: MainState by mutableStateOf(MainState.Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.onEach {
                    uiState = it
                }.collect()
            }
        }

        splashScreen.setKeepOnScreenCondition {
            when (uiState) {
                MainState.Loading -> true
                is MainState.Success -> false
            }
        }

        enableEdgeToEdge()

        setContent {
            val darkTheme = shouldUseDarkTheme(uiState)

            DisposableEffect(darkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        Color.TRANSPARENT,
                        Color.TRANSPARENT
                    ) { darkTheme },
                    navigationBarStyle = SystemBarStyle.auto(
                        Color.TRANSPARENT,
                        Color.TRANSPARENT
                    ) { darkTheme }
                )
                onDispose {}
            }

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
                        onResult = { result ->
                            val areGranted = result.values.reduce { acc, next -> acc && next }
                            if (areGranted) appState.navController.navigateToCamera()
                            else Unit
                        }
                    )
                    NeedItApp(
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
    uiState: MainState
): Boolean = when (uiState) {
    is MainState.Loading -> isSystemInDarkTheme()
    is MainState.Success -> {
        if (uiState.state.useSystemScheme) isSystemInDarkTheme()
        else uiState.state.isNightMode
    }
}