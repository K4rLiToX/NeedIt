package com.carlosdiestro.needit.features.camera

import android.content.Context
import android.content.res.Configuration
import androidx.camera.core.ImageCapture
import androidx.camera.view.PreviewView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.carlosdiestro.design_system.lists.WishCategoryPlo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal data class CameraDataState(
    val category: WishCategoryPlo = WishCategoryPlo.Clothes,
    val imageUri: String = ""
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun rememberCameraUiState(
    listState: LazyListState = rememberLazyListState(),
    snapBehavior: FlingBehavior = rememberSnapFlingBehavior(lazyListState = listState),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    context: Context = LocalContext.current,
    configuration: Configuration = LocalConfiguration.current,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    previewView: PreviewView = remember { PreviewView(context) }
): CameraUiState {
    return remember {
        CameraUiState(
            listState = listState,
            snapBehavior = snapBehavior,
            coroutineScope = coroutineScope,
            context = context,
            configuration = configuration,
            lifecycleOwner = lifecycleOwner,
            previewView = previewView
        )
    }
}

@Stable
internal class CameraUiState(
    val listState: LazyListState,
    val snapBehavior: FlingBehavior,
    val coroutineScope: CoroutineScope,
    val context: Context,
    val configuration: Configuration,
    val lifecycleOwner: LifecycleOwner,
    val previewView: PreviewView
) {
    val imageCapture: ImageCapture by mutableStateOf(ImageCapture.Builder().build())

    var step: Int by mutableIntStateOf(1)
        private set

    val shouldReloadCamera: Boolean
        get() = step == 1

    val selectedIndex: Int
        get() = listState.firstVisibleItemIndex

    val isScrollInProgress: Boolean
        get() = listState.isScrollInProgress

    val screenWidth: Dp
        get() = configuration.screenWidthDp.dp

    fun nextStep() {
        step++
    }

    fun prevStep() {
        step--
    }

    fun scrollToItem(index: Int) {
        coroutineScope.launch {
            listState.animateScrollToItem(index)
        }
    }
}