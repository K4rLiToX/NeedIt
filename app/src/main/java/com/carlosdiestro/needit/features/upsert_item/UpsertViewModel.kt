package com.carlosdiestro.needit.features.upsert_item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory
import com.carlosdiestro.needit.core.design_system.components.navigation.toWishCategory
import com.carlosdiestro.needit.domain.wishes.Book
import com.carlosdiestro.needit.domain.wishes.Clothes
import com.carlosdiestro.needit.domain.wishes.Footwear
import com.carlosdiestro.needit.domain.wishes.GetWishUseCase
import com.carlosdiestro.needit.domain.wishes.InsertWishUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpsertViewModel @Inject constructor(
    private val getWish: GetWishUseCase,
    private val insertWish: InsertWishUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val imageUrl: String = (savedStateHandle[argImageUrl] ?: "").replace("-", "/")
    private val category: WishCategory = (savedStateHandle[argCategory] ?: -1).toWishCategory()
    private val wishId: Long = savedStateHandle[argWishId] ?: -1L

    private var _state: MutableStateFlow<UpsertUiState> = MutableStateFlow(
        UpsertUiState(imageUrl, category)
    )
    val state = _state.asStateFlow()

    var title by mutableStateOf("")
        private set

    var subtitle by mutableStateOf("")
        private set

    var price by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var webUrl by mutableStateOf("")
        private set

    var size by mutableStateOf("")
        private set

    var color by mutableStateOf("")
        private set

    var isbn by mutableStateOf("")
        private set


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
                    category = wish.category
                )
            }
            updateTitle(wish.title)
            updateSubtitle(wish.subtitle)
            updatePrice(wish.price.toString())
            updateWebUrl(wish.webUrl)
            updateDescription(wish.description)
            when (wish) {
                is Clothes -> {
                    updateSize(wish.size)
                    updateColor(wish.color)
                }

                is Footwear -> {
                    updateSize(wish.size.toString())
                    updateColor(wish.color)
                }

                is Book -> {
                    updateIsbn(wish.isbn)
                }

                else -> Unit
            }
        }
    }

    fun updateTitle(value: String) {
        title = value
    }

    fun updateSubtitle(value: String) {
        subtitle = value
    }

    fun updatePrice(value: String) {
        price = value
    }

    fun updateWebUrl(value: String) {
        webUrl = value
    }

    fun updateDescription(value: String) {
        description = value
    }

    fun updateSize(value: String) {
        size = value
    }

    fun updateColor(value: String) {
        color = value
    }

    fun updateIsbn(value: String) {
        isbn = value
    }

    fun save() {
        viewModelScope.launch {
            insertWish(
                imageUrl = imageUrl,
                title = title,
                subtitle = subtitle,
                price = price,
                webUrl = webUrl,
                description = description,
                category = category,
                size = size,
                color = color,
                isbn = isbn
            )
        }
    }

    fun isFormFilledInCorrectly(): Boolean = title.trim().isNotEmpty()
}

data class UpsertUiState(
    val imageUrl: String,
    val category: WishCategory,
)