package com.carlosdiestro.needit.features.camera

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.buttons.NiButtonSpecs
import com.carlosdiestro.needit.core.design_system.components.buttons.NiFilledButton
import com.carlosdiestro.needit.core.design_system.components.icon_buttons.NiIconButtonSpecs
import com.carlosdiestro.needit.core.design_system.components.lists.NiCategoryScrollable
import com.carlosdiestro.needit.core.design_system.components.lists.WishCategoryPlo
import com.carlosdiestro.needit.core.design_system.components.navigation.top_app_bar.NiTopAppBar
import com.carlosdiestro.needit.core.design_system.components.navigation.top_app_bar.NiTopAppBarSpecs
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icons
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun CameraRoute(
    onBackClick: () -> Unit,
    onContinueClick: (Int, Long) -> Unit,
    viewModel: CameraViewModel = hiltViewModel()
) {
    val dataState by viewModel.state.collectAsStateWithLifecycle()
    val uiState = rememberCameraUiState()
    CameraScreen(
        dataState = dataState,
        uiState = uiState,
        onBackClick = onBackClick,
        onContinueClick = {
            onContinueClick(
                dataState.category.toIntValue(),
                -1
            )
        },
        onBackToPhotoClick = {
            viewModel.resetPhoto()
            uiState.prevStep()
        },
        onShutterClick = { imageUri ->
            viewModel.onShutterClick(imageUri)
            uiState.nextStep()
        },
        updateCategory = viewModel::updateCategory
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CameraScreen(
    dataState: CameraDataState,
    uiState: CameraUiState,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit,
    onBackToPhotoClick: () -> Unit,
    onShutterClick: (String) -> Unit,
    updateCategory: (WishCategoryPlo) -> Unit
) {
    val preview =
        Preview.Builder()
            .build()
            .also { it.setSurfaceProvider(uiState.previewView.surfaceProvider) }

    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
        .build()

    LaunchedEffect(uiState.shouldReloadCamera) {
        uiState.context.getCameraProvider().apply {
            unbindAll()
            bindToLifecycle(
                uiState.lifecycleOwner,
                cameraSelector,
                preview,
                uiState.imageCapture
            )
        }
    }

    Scaffold(
        topBar = {
            NiTopAppBar(
                title = "",
                onNavigationClick = {
                    if (uiState.shouldReloadCamera) onBackClick()
                    else onBackToPhotoClick()
                },
                navigationIconColor = NiIconButtonSpecs.Color.transparentSecondary(),
                colors = NiTopAppBarSpecs.Color.neutral(),
                actions = {}
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            CameraContent(
                uiState = uiState,
                imageUri = dataState.imageUri,
                onShutterClick = onShutterClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 24.dp,
                            bottomEnd = 24.dp
                        )
                    )
                    .weight(1F)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingM))
            Actions(
                uiState = uiState,
                categories = WishCategoryPlo.values().toList(),
                updateCategory = updateCategory,
                onContinueClick = onContinueClick
            )
        }
    }
}

@Composable
private fun CameraContent(
    modifier: Modifier = Modifier,
    uiState: CameraUiState,
    imageUri: String,
    onShutterClick: (String) -> Unit
) {
    when (uiState.step) {
        1 -> CameraPreview(
            previewView = uiState.previewView,
            coroutineScope = uiState.coroutineScope,
            context = uiState.context,
            imageCapture = uiState.imageCapture,
            onShutterClick = onShutterClick,
            modifier = modifier
        )

        else -> PhotoPreview(
            imageUri = imageUri,
            modifier = modifier
        )
    }
}

@Composable
private fun Actions(
    uiState: CameraUiState,
    categories: List<WishCategoryPlo>,
    updateCategory: (WishCategoryPlo) -> Unit,
    onContinueClick: () -> Unit
) {
    when (uiState.step) {
        1 -> {
            LaunchedEffect(uiState.selectedIndex, uiState.isScrollInProgress) {
                if (!uiState.isScrollInProgress) {
                    updateCategory(categories[uiState.selectedIndex])
                }
            }
            NiCategoryScrollable(
                categories = categories,
                selectedIndex = uiState.selectedIndex,
                listState = uiState.listState,
                snapBehavior = uiState.snapBehavior,
                screenWidth = uiState.screenWidth,
                onCategoryClick = uiState::scrollToItem,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.dimensions.spacingXS)
            )
        }

        else -> {
            NiFilledButton(
                labelId = R.string.button_continue,
                trailIcon = MaterialTheme.icons.Continue,
                height = NiButtonSpecs.Height.Large,
                onClick = onContinueClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.dimensions.spacingM)
                    .padding(bottom = MaterialTheme.dimensions.spacingM)
            )
        }
    }
}

@Composable
private fun CameraPreview(
    modifier: Modifier = Modifier,
    previewView: PreviewView,
    coroutineScope: CoroutineScope,
    context: Context,
    imageCapture: ImageCapture,
    onShutterClick: (String) -> Unit
) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = modifier
    ) {
        AndroidView(
            factory = { previewView },
            modifier = Modifier
                .matchParentSize()
        )

        CameraShutter(
            coroutineScope = coroutineScope,
            context = context,
            imageCapture = imageCapture,
            onShutterClick = onShutterClick
        )
    }
}

@Composable
private fun CameraShutter(
    coroutineScope: CoroutineScope,
    context: Context,
    imageCapture: ImageCapture,
    onShutterClick: (String) -> Unit
) {
    val contentResolver = LocalContext.current.applicationContext.contentResolver
    IconButton(
        onClick = {
            coroutineScope.launch {
                imageCapture.takePhoto(
                    executor = context.executor,
                    contentResolver = contentResolver,
                    onImageCapture = onShutterClick
                )
            }
        },
        modifier = Modifier
            .padding(bottom = MaterialTheme.dimensions.spacingM)
            .size(80.dp)
    ) {
        Icon(
            imageVector = MaterialTheme.icons.Lens,
            contentDescription = "Take picture",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .size(100.dp)
                .padding(1.dp)
                .border(2.dp, MaterialTheme.colorScheme.onPrimary, CircleShape)
        )
    }
}

@Composable
private fun PhotoPreview(
    modifier: Modifier = Modifier,
    imageUri: String
) {
    AsyncImage(
        model = imageUri,
        contentDescription = "Photo",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
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

fun ImageCapture.takePhoto(
    executor: Executor,
    contentResolver: ContentResolver,
    onImageCapture: (String) -> Unit
) {
    val outputOptions = createOutputOptions(contentResolver)
    takePicture(
        outputOptions,
        executor,
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                onImageCapture(outputFileResults.savedUri.toString())
            }

            override fun onError(exception: ImageCaptureException) {
                TODO("Not yet implemented")
            }
        }
    )
}

private fun createOutputOptions(
    contentResolver: ContentResolver
): ImageCapture.OutputFileOptions = ImageCapture.OutputFileOptions
    .Builder(
        contentResolver,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        createFileMetadata()
    ).build()

private fun createFileMetadata(): ContentValues = ContentValues().apply {
    put(MediaStore.MediaColumns.DISPLAY_NAME, createFileName())
    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
        put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/NeedIt")
    }
}

private fun createFileName(): String = SimpleDateFormat(
    "yyyyMMddHHmmSS",
    Locale.getDefault()
).format(System.currentTimeMillis())