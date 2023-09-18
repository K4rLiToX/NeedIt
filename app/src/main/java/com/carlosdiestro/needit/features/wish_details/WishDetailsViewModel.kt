package com.carlosdiestro.needit.features.wish_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.domain.wishes.usecases.GetWishUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishDetailsViewModel @Inject constructor(
    private val getWish: GetWishUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val wishId = savedStateHandle[argsWishId] ?: -1L
    private var _state: MutableStateFlow<WishDetailsUiState> =
        MutableStateFlow(WishDetailsUiState())
    val state = _state.asStateFlow()

    init {
        fetchWish()
    }

    private fun fetchWish() {
        viewModelScope.launch {
            val wish = getWish(wishId)
            _state.update {
                it.copy(
                    imageUrl = wish.imageUrl.ifEmpty { wish.imageLocalPath },
                    title = wish.title,
                    subtitle = wish.subtitle,
                    price = wish.price.toString(),
                    description = wish.description,
                    webUrl = wish.webUrl,
                    isShared = wish.isShared,
                    size = wish.size,
                    color = wish.color,
                    isbn = wish.isbn
                )
            }
        }
    }
}

data class WishDetailsUiState(
    val imageUrl: String = "",
    val title: String = "",
    val subtitle: String = "",
    val price: String = "0.0",
    val description: String = "",
    val webUrl: String = "",
    val isShared: Boolean = false,
    val size: String? = null,
    val color: String? = null,
    val isbn: String? = null
)