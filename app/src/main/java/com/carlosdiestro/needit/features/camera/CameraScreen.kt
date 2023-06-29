package com.carlosdiestro.needit.features.camera

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItFilledButton
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItFilledIconButton
import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory
import com.carlosdiestro.needit.core.design_system.theme.icons
import com.carlosdiestro.needit.core.design_system.theme.spacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.math.absoluteValue

@Composable
fun CameraRoute(
    coroutineScope: CoroutineScope,
    onBackClick: () -> Unit,
    viewModel: CameraViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CameraScreen(
        state = state,
        coroutineScope = coroutineScope,
        onBackClick = onBackClick,
        onBackToPhotoClick = viewModel::onBackToPhotoClick,
        onShutterClick = viewModel::onShutterClick,
        updateCategory = viewModel::updateCategory
    )
}

@Composable
private fun CameraScreen(
    state: CameraUiState,
    coroutineScope: CoroutineScope,
    onBackClick: () -> Unit,
    onBackToPhotoClick: () -> Unit,
    onShutterClick: (String) -> Unit,
    updateCategory: (WishCategory) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val previewView = remember {
        PreviewView(context)
    }

    val preview =
        Preview.Builder()
            .build()
            .also { it.setSurfaceProvider(previewView.surfaceProvider) }

    val imageCapture by remember {
        mutableStateOf(
            ImageCapture.Builder().build()
        )
    }

    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
        .build()

    LaunchedEffect(key1 = state.step == CameraStep.Photo) {
        context.getCameraProvider().apply {
            unbindAll()
            bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                imageCapture
            )
        }
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.spacing.m)
            .padding(bottom = MaterialTheme.spacing.l)
    ) {
        CameraContent(
            previewView = previewView,
            imageUri = state.imageUri,
            onBackClick = onBackClick,
            onBackToPhotoClick = onBackToPhotoClick,
            cameraStep = state.step,
            updateCategory = updateCategory
        )
        Actions(
            coroutineScope = coroutineScope,
            context = context,
            imageCapture = imageCapture,
            cameraStep = state.step,
            onShutterClick = onShutterClick
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CameraContent(
    previewView: PreviewView,
    imageUri: String,
    cameraStep: CameraStep,
    onBackClick: () -> Unit,
    onBackToPhotoClick: () -> Unit,
    updateCategory: (WishCategory) -> Unit
) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.85f)
            .clip(RoundedCornerShape(24.dp))
    ) {
        when (cameraStep) {
            CameraStep.Photo -> CameraPreview(
                previewView = previewView
            )

            CameraStep.Category -> CategorySelector(
                imageUri = imageUri
            )
        }
        if (cameraStep == CameraStep.Category) {
            val pagerState = rememberPagerState(
                initialPage = 0,
                pageCount = { WishCategory.values().size - 1 }
            )
            LaunchedEffect(key1 = pagerState) {
                snapshotFlow { pagerState.currentPage }.collect {
                    updateCategory(WishCategory.values()[it + 1])
                }
            }
            VerticalPager(
                state = pagerState,
                contentPadding = PaddingValues(vertical = 280.dp),
                pageSize = PageSize.Fixed(90.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MaterialTheme.spacing.m)
            ) {
                val pageOffset = (
                        (pagerState.currentPage - it) + pagerState
                            .currentPageOffsetFraction
                        ).absoluteValue
                Text(
                    text = stringResource(id = WishCategory.values()[it + 1].labelId),
                    style = MaterialTheme.typography.headlineLarge,
                    color = if (it == pagerState.currentPage) MaterialTheme.colorScheme.onSurface
                            else MaterialTheme.colorScheme.surface,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .pagerAnimation(pageOffset)
                        .clip(RoundedCornerShape(100))
                        .then(
                            if (it == pagerState.currentPage) {
                                Modifier.background(MaterialTheme.colorScheme.surface)
                            } else {
                                Modifier.background(Color.Transparent)
                            }
                        )
                        .padding(MaterialTheme.spacing.m)
                        .fillMaxWidth()
                )
            }
        }
        NeedItFilledIconButton(
            icon = MaterialTheme.icons.Back,
            modifier = Modifier.padding(MaterialTheme.spacing.m),
            onClick = {
                when (cameraStep) {
                    CameraStep.Photo -> onBackClick()
                    CameraStep.Category -> onBackToPhotoClick()
                }
            }
        )
    }
}

@Composable
private fun Actions(
    coroutineScope: CoroutineScope,
    context: Context,
    imageCapture: ImageCapture,
    cameraStep: CameraStep,
    onShutterClick: (String) -> Unit
) {
    when (cameraStep) {
        CameraStep.Photo -> CameraShutter(
            coroutineScope = coroutineScope,
            context = context,
            imageCapture = imageCapture,
            onShutterClick = onShutterClick
        )

        CameraStep.Category -> ContinueButton()
    }
}

@Composable
private fun CameraPreview(
    previewView: PreviewView
) {
    AndroidView(
        factory = { previewView },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun CameraShutter(
    coroutineScope: CoroutineScope,
    context: Context,
    imageCapture: ImageCapture,
    onShutterClick: (String) -> Unit
) {
    IconButton(
        onClick = {
            coroutineScope.launch {
                val imageUri = imageCapture
                    .takePhoto(context.executor)
                    .toString()
                onShutterClick(imageUri)
            }
        },
        modifier = Modifier
            .padding(bottom = MaterialTheme.spacing.xs)
            .size(80.dp)
    ) {
        Icon(
            imageVector = MaterialTheme.icons.Lens,
            contentDescription = "Take picture",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(100.dp)
                .padding(1.dp)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
    }
}

@Composable
private fun CategorySelector(
    imageUri: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onSurface)
            .alpha(0.5F)
            .fillMaxSize()
    ) {
        AsyncImage(
            model = imageUri,
            contentDescription = "Photo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

fun Modifier.pagerAnimation(pageOffset: Float): Modifier =
    graphicsLayer {
        lerp(
            start = 0.75F,
            stop = 1F,
            fraction = 1F - pageOffset.coerceIn(0F, 1F)
        ).also { scale ->
            scaleX = scale
            scaleY = scale
        }

        alpha = lerp(
            start = 0.5f,
            stop = 1f,
            fraction = 1f - pageOffset.coerceIn(0f, 1f)
        )
    }

@Composable
private fun ContinueButton() {
    NeedItFilledButton(
        labelId = R.string.button_continue,
        trailingIcon = MaterialTheme.icons.Continue,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {

    }
}

suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine { continuation ->
    ProcessCameraProvider.getInstance(this).also { future ->
        future.addListener({
            continuation.resume(future.get())
        }, executor)
    }
}

private val Context.executor: Executor
    get() = ContextCompat.getMainExecutor(this)

suspend fun ImageCapture.takePhoto(executor: Executor): File {
    val file = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            File.createTempFile("image", ".jpg")
        }.getOrElse {
            File("/dev/null")
        }
    }
    return suspendCoroutine { continuation ->
        val outputOptions = ImageCapture.OutputFileOptions.Builder(file).build()
        takePicture(outputOptions, executor, object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                continuation.resume(file)
            }

            override fun onError(exception: ImageCaptureException) {
                continuation.resumeWithException(exception)
            }
        })
    }
}