package com.carlosdiestro.needit.features.camera

import androidx.lifecycle.ViewModel
import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor() : ViewModel() {

    private var _state: MutableStateFlow<CameraUiState> = MutableStateFlow(CameraUiState())
    val state = _state.asStateFlow()

    fun onShutterClick(imageUri: String) {
        _state.update {
            it.copy(
                imageUri = imageUri,
                step = CameraStep.Category
            )
        }
    }

    fun onBackToPhotoClick() {
        _state.update {
            it.copy(
                step = CameraStep.Photo
            )
        }
    }

    fun updateCategory(wishCategory: WishCategory) {
        _state.update {
            it.copy(
                category = wishCategory
            )
        }
    }
}


data class CameraUiState(
    val imageUri: String = "",
    val category: WishCategory = WishCategory.Clothes,
    val step: CameraStep = CameraStep.Photo
)

enum class CameraStep {
    Photo,
    Category
}