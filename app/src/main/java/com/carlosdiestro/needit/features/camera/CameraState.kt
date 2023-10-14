package com.carlosdiestro.needit.features.camera

import android.content.Context
import androidx.camera.core.ImageCapture
import androidx.camera.view.PreviewView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import com.carlosdiestro.needit.core.design_system.components.lists.WishCategoryPlo
import kotlinx.coroutines.CoroutineScope

data class CameraDataState(
    val category: WishCategoryPlo = WishCategoryPlo.Clothes,
    val imageUri: String = ""
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun rememberCameraUiState(
    pagerState: PagerState,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    context: Context = LocalContext.current,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    previewView: PreviewView = remember { PreviewView(context) }
): CameraUiState {
    return remember {
        CameraUiState(
            pagerState = pagerState,
            coroutineScope = coroutineScope,
            context = context,
            lifecycleOwner = lifecycleOwner,
            previewView = previewView
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Stable
class CameraUiState(
    val pagerState: PagerState,
    val coroutineScope: CoroutineScope,
    val context: Context,
    val lifecycleOwner: LifecycleOwner,
    val previewView: PreviewView
) {
    val imageCapture: ImageCapture by mutableStateOf(ImageCapture.Builder().build())

    var step: Int by mutableIntStateOf(1)
        private set

    val shouldReloadCamera: Boolean
        get() = step == 1

    val currentPage: Int
        get() = pagerState.currentPage

    val isScrollInProgress: Boolean
        get() = pagerState.isScrollInProgress

    fun nextStep() {
        step++
    }

    fun prevStep() {
        step--
    }

    suspend fun scrollToPage(index: Int) {
        pagerState.animateScrollToPage(index)
    }
}