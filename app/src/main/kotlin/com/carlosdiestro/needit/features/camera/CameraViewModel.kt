package com.carlosdiestro.needit.features.camera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.core.design_system.components.lists.WishCategoryPlo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class CameraViewModel @Inject constructor() : ViewModel() {

    private var _selectedCategory: MutableStateFlow<WishCategoryPlo> =
        MutableStateFlow(WishCategoryPlo.Clothes)

    private var _imageUri: MutableStateFlow<String> = MutableStateFlow("")

    val state: StateFlow<CameraDataState> =
        combine(
            _selectedCategory,
            _imageUri
        ) { category, uri ->
            CameraDataState(
                category = category,
                imageUri = uri
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CameraDataState()
        )

    fun updateCategory(wishCategory: WishCategoryPlo) {
        _selectedCategory.update {
            wishCategory
        }
    }

    fun onShutterClick(imageUri: String) {
        _imageUri.update {
            imageUri
        }
    }

    fun resetPhoto() {
        _imageUri.update {
            ""
        }
    }
}
