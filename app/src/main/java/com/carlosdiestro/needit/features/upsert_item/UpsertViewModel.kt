package com.carlosdiestro.needit.features.upsert_item

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory
import com.carlosdiestro.needit.core.design_system.components.navigation.toWishCategory
import com.carlosdiestro.needit.domain.wishes.Book
import com.carlosdiestro.needit.domain.wishes.Clothes
import com.carlosdiestro.needit.domain.wishes.Footwear
import com.carlosdiestro.needit.domain.wishes.GetWishUseCase
import com.carlosdiestro.needit.domain.wishes.Other
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpsertViewModel @Inject constructor(
    private val getWish: GetWishUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val imageUrl: String = savedStateHandle[argImageUrl] ?: ""
    private val category: WishCategory = (savedStateHandle[argCategory] ?: -1).toWishCategory()
    private val wishId: Long = savedStateHandle[argWishId] ?: -1L

    private var _state: MutableStateFlow<UpsertUiState> = MutableStateFlow(
        UpsertUiState(imageUrl, category)
    )
    val state = _state.asStateFlow()

    init {
        if (wishId != -1L) {
            fetchWish()
        }
    }

    private fun fetchWish() {
        viewModelScope.launch {
            val wish = getWish(wishId)
            _state.update {
                it.copy(
                    imageUrl = wish.imageUrl,
                    category = wish.category,
                    title = wish.title,
                    subtitle = wish.subtitle,
                    price = wish.price,
                    description = wish.description,
                    webUrl = wish.webUrl
                )
            }
            _state.update {
                when (wish) {
                    is Clothes -> it.copy(
                        size = wish.size,
                        color = wish.color
                    )
                    is Footwear -> it.copy(
                        size = wish.size.toString(),
                        color = wish.color
                    )
                    is Book -> it.copy(
                        isbn = wish.isbn
                    )
                    else -> it
                }
            }
        }
    }
}

data class UpsertUiState(
    val imageUrl: String,
    val category: WishCategory,
    val title: String = "",
    val subtitle: String = "",
    val price: Double = 0.0,
    val webUrl: String = "",
    val description: String = "",
    val size: String? = null,
    val color: String? = null,
    val isbn: String? = null
)